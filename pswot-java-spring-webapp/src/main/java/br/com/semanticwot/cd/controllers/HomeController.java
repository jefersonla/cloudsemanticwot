/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.controllers;

import br.com.semanticwot.cd.daos.ISwotApplicationDAO;
import br.com.semanticwot.cd.models.GatewayForm;
import br.com.semanticwot.cd.models.SwotApplication;
import br.com.semanticwot.cd.models.SystemUser;
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
@Transactional
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ISwotApplicationDAO swotApplicationDAO;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("index");

        SwotApplication swotApplication = new SwotApplication();

        try {
            swotApplication = swotApplicationDAO.findOne(
                    (SystemUser) authentication.getPrincipal());
        } catch (EmptyResultDataAccessException ex) {
        } catch (Exception ex) {}

        modelAndView.addObject("swotapplication", swotApplication);
        modelAndView.addObject("gatewayform", new GatewayForm());

        return modelAndView;
    }
    
}
