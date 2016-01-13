/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.util;

/**
 *
 * @author nailton
 */
public enum PerfilStatus {

    PUBLIC("PUBLIC"), PRIVATE("PRIVATE");
    
    private String description;

    PerfilStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
