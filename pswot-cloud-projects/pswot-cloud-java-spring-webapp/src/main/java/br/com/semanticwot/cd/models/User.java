/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.models;

import br.com.semanticwot.cd.util.PerfilStatus;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author nailton
 */
public class User {
    
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String login;
    @NotNull
    private PerfilStatus perfilstatus;

    public PerfilStatus getPerfilstatus() {
        return perfilstatus;
    }

    public void setPerfilstatus(PerfilStatus perfilstatus) {
        this.perfilstatus = perfilstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
