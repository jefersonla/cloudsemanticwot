/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.infra;

/**
 * Classe para execução de comandos locais, ela pode encerrar processos
 *
 * @author nailton
 */
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LocalShell implements Serializable {

    private ProcessBuilder p;
    private Process process;

    private static final Logger log = Logger.getLogger(LocalShell.class
            .getName());

    public void executeCommand(final String command) {
        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);
        BufferedReader br = null;
        try {
            p = new ProcessBuilder(commands);
            this.process = p.start();
//            final InputStream is = process.getInputStream();
//            final InputStreamReader isr = new InputStreamReader(is);
//            br = new BufferedReader(isr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println("Retorno do comando = [" + line + "]");
//            }
        } catch (IOException ioe) {
            log.log(Level.SEVERE, "Erro ao executar comando shell{0}", ioe
                    .getMessage());
        } finally {
            secureClose(br);
        }
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Erro = {0}", ex.getMessage());
        }
    }

    // Solução provisória
    public void stopCommand() {
        //this.process.destroy();
        executeCommand("killall node-red");
    }
}
