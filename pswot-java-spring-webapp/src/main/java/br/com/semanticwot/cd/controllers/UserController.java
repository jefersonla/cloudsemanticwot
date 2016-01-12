/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.controllers;

import br.com.semanticwot.cd.daos.IRuleDAO;
import br.com.semanticwot.cd.daos.IUserDAO;
import br.com.semanticwot.cd.daos.RuleDAO;
import br.com.semanticwot.cd.daos.UserDAO;
import br.com.semanticwot.cd.exceptions.SettingsNodeRedNotCreated;
import br.com.semanticwot.cd.exceptions.UserEmailExists;
import br.com.semanticwot.cd.infra.MailManager;
import br.com.semanticwot.cd.infra.SSHManager;
import br.com.semanticwot.cd.models.Role;
import br.com.semanticwot.cd.models.SystemUser;
import br.com.semanticwot.cd.models.User;
import br.com.semanticwot.cd.util.Constants;
import br.com.semanticwot.cd.util.EmailTemplates;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nailton
 */
@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private IRuleDAO ruleDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private MailManager mailManager;

    private static final Logger LOGGER = Logger.getLogger(UserController.class
            .getName());

    @RequestMapping(method = RequestMethod.POST, name = "saveUser")
    public ModelAndView save(@Valid User user,
            BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Authentication authentication, HttpServletRequest request) {

        System.out.println("Entrei no save");

        if (bindingResult.hasErrors()) { // Aqui que ele verifica a validação
            return form(user, authentication); // Se tiver erro ele redireciona, se não tratar o erro da erro ao tentar salvar
        }

        // Aqui eu tenho que converter a senha para crypto e adicionar as rules, para então eu criar um SystemUser
        SystemUser systemUser = new SystemUser();
        systemUser.setName(user.getName());
        BCryptPasswordEncoder senhaBCrypt = new BCryptPasswordEncoder();
        systemUser.setPassword(senhaBCrypt.encode(user.getPassword())); // Encripta o password

        // Tentar salvar ou atualizar o usuário
        if (authentication != null
                && (authentication.getPrincipal() instanceof SystemUser)) {
            SystemUser userAuth = (SystemUser) authentication.getPrincipal();

            // Verificando se ele tentou enviar um email diferente
            if (!userAuth.getLogin().equals(user.getLogin())) {
                redirectAttributes.addFlashAttribute("info",
                        "The email can not be changed");
                return new ModelAndView("redirect:user/form");
            }
            // Definindo valores que nao podem ser alterados
            systemUser.setLogin(userAuth.getLogin());
            systemUser.setIp(userAuth.getIp());
            systemUser.setPort(userAuth.getPort());
            
            userDAO.update(systemUser);

            redirectAttributes.addFlashAttribute("info",
                    "User updated successfully");

            // Criando um novo usuário
        } else {
            systemUser.setLogin(user.getLogin());
            List<Role> list = new ArrayList<>();
            list.add(ruleDAO.findByName("ROLE_ADMIN"));
            systemUser.setRoles(list);

            // Associando o endereco do usuario a instancia do nodered
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            systemUser.setIp(ipAddress);
            // Gerando uma porta para as portas liberadas no servidor
            systemUser.setPort((int) (1880 + Math.random() * 200));

            // Capturar o erro aqui, para não enviar o Email e 
            // também não deixar o erro chegar no usuario final!
            if (userDAO.findOne(user.getLogin()) != null) {
                throw new UserEmailExists("Mail address "
                        + "is already registered");
            }

            // Criar o arquivo settings dele
            try {
                createSettingsNodeRed(systemUser);
            } catch (SettingsNodeRedNotCreated ex) {
                Logger.getLogger(UserController.class.getName())
                        .log(Level.SEVERE, null, ex);
                throw new SettingsNodeRedNotCreated("Error when trying "
                        + "to create the user");
            }

            // Configurar servidor para aceitar na porta requisicoes so desse IP
            serverConfigure(ipAddress, systemUser.getPort());
            System.out.println(ipAddress);

            // Chegou aqui, esta tudo pronto para criar o usuario
            userDAO.create(systemUser);

            // Mensagem de sucesso para a tela de login
            redirectAttributes.addFlashAttribute("info",
                    "User created successfully");

            try {
                // Enviando o Email
                mailManager.sendNewPurchaseMail(systemUser,
                        EmailTemplates.registerTemplate);
                // Se Email não for enviado
            } catch (MessagingException ex) {
                Logger.getLogger(UserController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        }

        return new ModelAndView("redirect:login");
    }

    @RequestMapping("/form")
    public ModelAndView form(User user, Authentication authentication) { // Esse objeto produto é injetado automáticamento na view, porque ele está entrando como parâmetro, mesmo que ele não exista, é injetado como null dessa forma, não da erro quando eu tento acessar um product na view
        if (authentication != null
                && (authentication.getPrincipal() instanceof SystemUser)) {
            SystemUser systemUser = (SystemUser) authentication.getPrincipal();
            user.setLogin(systemUser.getLogin());
            user.setName(systemUser.getName());
            user.setPassword("");
        }
        ModelAndView modelAndView = new ModelAndView("user/form");
        return modelAndView;
    }

    // Esse método deve configurar o servidor para só aceitar requisições desse 
    // IP a uma porta especifica
    private void serverConfigure(String ipAddress, int port) {
    }

    private void createSettingsNodeRed(SystemUser systemUser)
            throws SettingsNodeRedNotCreated {
        try {
            File file = new File(System.getProperty("user.home")
                    + System.getProperty("file.separator")
                    + Constants.NODERED_PATH
                    + System.getProperty("file.separator")
                    + Constants.SETTINGS_TEMPLATE
            );

            // O caminho esta correto
            //System.out.println(System.getProperty("user.home")
            //        + System.getProperty("file.separator")
            //        + Constants.NODERED_PATH
            //        + System.getProperty("file.separator")
            //        + Constants.SETTINGS_TEMPLATE);
            // Esta lendo o arquivo de boas
            //System.out.println(new String(Files.readAllBytes(file.toPath())));
            String text = new String(Files.readAllBytes(file.toPath()));

            File formated = new File(System.getProperty("user.home")
                    + System.getProperty("file.separator")
                    + Constants.NODERED_PATH
                    + System.getProperty("file.separator")
                    + "settings_"
                    + systemUser.getLogin()
                    + ".js"
            );

            FileWriter fw = new FileWriter(formated.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text.replace("{0}", String.valueOf(systemUser.getPort())));
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new SettingsNodeRedNotCreated("Settings not created");
        }

    }

    /* TRATAMENTO DE ERROS COM @ExceptionHandler */
    // Voce pode capturar vários tipos de erro, é só usar a @ExceptionHandler
    @ExceptionHandler({UserEmailExists.class, SettingsNodeRedNotCreated.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        LOGGER.log(Level.WARNING, "Request: {0} raised {1}",
                new Object[]{req.getRequestURL(),
                    exception});

        ModelAndView mav = new ModelAndView();
        mav.addObject("info", exception.getMessage());
        mav.addObject("user", new User());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("user/form");
        return mav;
    }

            // Convert a predefined exception to an HTTP Status code
    /*
     @ResponseStatus(value = HttpStatus.CONFLICT, reason
     = "Data integrity violation") // 409
     @ExceptionHandler(DataIntegrityViolationException.class)
     public void conflict() {
     // Nothing to do
     }
     */
    // Trata o erro, o Spring MVC gerencia os erros que vem do DAO
    // Parace que tem que passar esses parametros para funcionar, 
    // cabeçalho
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
//        LOGGER.log(Level.WARNING, "Request: {0} raised {1}",
//                new Object[]{req.getRequestURL(),
//                    exception});
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("info", exception.getMessage());
//        mav.addObject("user", new User());
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("user/form");
//        return mav;
//    }
}
