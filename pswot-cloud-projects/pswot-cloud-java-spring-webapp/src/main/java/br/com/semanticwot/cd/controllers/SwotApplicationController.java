/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.controllers;

import br.com.semanticwot.cd.daos.ISwotApplicationDAO;
import br.com.semanticwot.cd.infra.LocalShell;
import br.com.semanticwot.cd.infra.SSHManager;
import br.com.semanticwot.cd.models.SwotApplication;
import br.com.semanticwot.cd.models.SystemUser;
import br.com.semanticwot.cd.models.SwotApplicationForm;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nailton
 */
@Controller
@RequestMapping("/application")
@Transactional
public class SwotApplicationController {

    @Autowired
    private ISwotApplicationDAO swotApplicationDAO;

    private static final Logger LOGGER = Logger.getLogger(UserController.class
            .getName());

    private final LocalShell shell = new LocalShell();

    @RequestMapping(method = RequestMethod.POST, name = "saveApplication")
    public ModelAndView save(
            @Valid SwotApplicationForm swotApplicationForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return form(swotApplicationForm, authentication);
        }

        SystemUser systemUser = (SystemUser) authentication.getPrincipal();

        SwotApplication swotApplication = null;

        try {
            swotApplication = swotApplicationDAO.findOne(systemUser);
        } catch (EmptyResultDataAccessException ex) {
        }

        if (swotApplication == null) {
            swotApplication = new SwotApplication();
        }

        swotApplication.setName(swotApplicationForm.getName());
        swotApplication.setDescription(swotApplicationForm.getDescription());

        Calendar calendar = new GregorianCalendar();
        swotApplication.setReleaseDate(calendar);
        swotApplication.setSystemUser(systemUser);

        swotApplicationDAO.update(swotApplication);

        return new ModelAndView("redirect:/#three");
    }

    @RequestMapping("/form")
    public ModelAndView form(SwotApplicationForm swotApplicationForm,
            Authentication authentication) {

        SystemUser systemUser = (SystemUser) authentication.getPrincipal();

        SwotApplication swotApplication = null;

        try {
            swotApplication = swotApplicationDAO.findOne(systemUser);
        } catch (EmptyResultDataAccessException ex) {
        }

        // Carrega o formulario com os dados da aplicacao do usuario
        if (swotApplication != null) {
            swotApplicationForm.setDescription(swotApplication.getDescription());
            swotApplicationForm.setName(swotApplication.getName());
        }

        // Pegar essa porta do arquivo de configuracao
        int port = systemUser.getPort();

        ModelAndView modelAndView = new ModelAndView("application/form");
        modelAndView.addObject("noderedport", String.valueOf(port));
        modelAndView.addObject("infonode", "<a>http://localhost:" + port
                + "</a> is your node-RED instance. Use this URL "
                + "for access HTTP nodes in node-RED. For example: http://localhost:"
                + port
                + "/myservice");
        // Inicializar o node-red com um json e settings baseado no id do usuario
        // Enviar para a view o caminho para o iframe
        // Escrever os passos no lado direito
        // Tenho que finalizar o node? talvez só quando acabar a sessão do usuário
        startNodeRed(systemUser);

        return modelAndView;
    }

    @RequestMapping("/stop")
    public ModelAndView stop() {
        System.out.println("Parando o processo " + shell);
        shell.stopCommand();
        return new ModelAndView("redirect:/#three");
    }

    @RequestMapping("/deploy")
    public ModelAndView deploy(Authentication authentication) {
        System.out.println("Iniciando o processo " + shell);
        startNodeRed((SystemUser) authentication.getPrincipal());
        return new ModelAndView("redirect:/#three");
    }

    private void startNodeRed(SystemUser systemUser) {
        System.out.println("Iniciando o processo " + shell);
        String nodeCommand = "cd " 
                + System.getProperty("user.home")
                + System.getProperty("file.separator")
                + "node-red && node-red "
                + "--settings settings_"
                + systemUser.getLogin() 
                + ".js " 
                + systemUser.getLogin() 
                + ".json";
        System.out.println(nodeCommand);
        shell.executeCommand(nodeCommand);
    }

}
