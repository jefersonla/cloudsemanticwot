/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.infra;

/**
 *
 * @author nailton
 */
public enum TransferProtocolType {

    SFTP("sftp");

    public String valor;

    TransferProtocolType(String valor) {
        this.valor = valor;
    }

}
