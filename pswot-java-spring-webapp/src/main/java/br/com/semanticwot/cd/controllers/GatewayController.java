/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.controllers;

import br.com.semanticwot.cd.daos.ISwotApplicationDAO;
import br.com.semanticwot.cd.exceptions.GatewayWotNotCreated;
import br.com.semanticwot.cd.infra.SSHManager;
import br.com.semanticwot.cd.models.GatewayForm;
import br.com.semanticwot.cd.models.SwotApplication;
import br.com.semanticwot.cd.models.SwotApplicationForm;
import br.com.semanticwot.cd.models.SystemUser;
import br.com.semanticwot.cd.infra.TransferProtocolType;
import br.com.semanticwot.cd.util.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nailton
 */
@Controller
@Transactional
@RequestMapping("/gateway")
public class GatewayController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class
            .getName());

    @RequestMapping(method = RequestMethod.POST, name = "saveGateway")
    public ModelAndView save(
            @Valid @ModelAttribute("gateway") GatewayForm gatewayForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Authentication authentication) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());
            System.out
                    .println(bindingResult.getFieldError().getDefaultMessage());
            System.out.println("ERRO FORM");
            return form(gatewayForm, authentication);
        }

        // Instalacao do gateway
        // 1) verificar envio de arquivo grande, e analisar o comportamento do navegador
        SSHManager sSHManager = new SSHManager(
                gatewayForm.getUser(),
                gatewayForm.getPassword(),
                gatewayForm.getIp(),
                "");

        String errorMessage = sSHManager.connect();

        if (errorMessage != null) {
            System.out.println(errorMessage);
        }
        
        // Talvez usar server sent events aqui, mais nao quero perder mais tempo
        
        try {
            sSHManager.sendFile(
                    System.getProperty("user.home")
                    + System.getProperty("file.separator")
                    + Constants.PSWOT_GATEWAY_PATH
                    + System.getProperty("file.separator")
                    + Constants.PSWOT_GATEWAY_FILE,
                    "/tmp/novo_host.zip",
                    TransferProtocolType.SFTP);
        } catch (RuntimeException ex) {
            throw new GatewayWotNotCreated(
                    "Gateway WoT not installed, try again.");
        }

        sSHManager.close();

        System.out.println("Imprimindo no SAVE " + gatewayForm.toString());

        return new ModelAndView("redirect:/#install");
    }

    // @ModelAttribute define o nome do ComandName do form, 
    // caso contrario ele vai ser o nome do parametro
    @RequestMapping("/form")
    private ModelAndView form(@ModelAttribute("gateway") GatewayForm gatewayForm,
            Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("gateway/form");

        return modelAndView;
    }

    /* TRATAMENTO DE ERROS */
    @ExceptionHandler({GatewayWotNotCreated.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        LOGGER.log(Level.WARNING, "Request: {0} raised {1}",
                new Object[]{req.getRequestURL(),
                    exception});

        ModelAndView mav = new ModelAndView();
        mav.addObject("info", exception.getMessage());
        mav.addObject("gateway", new GatewayForm());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("gateway/form");
        return mav;
    }

}
