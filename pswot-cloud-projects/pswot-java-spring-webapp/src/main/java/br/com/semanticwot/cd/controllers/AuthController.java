package br.com.semanticwot.cd.controllers;

//import br.com.semanticwot.cd.infra.LocalShell;
import br.com.semanticwot.cd.infra.SSHManager;
import br.com.semanticwot.cd.infra.TransferProtocolType;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/login")
    public String loginPage() throws IOException {
        // Teste para executar o node-red para um usuário especifico.
        //final LocalShell shell = new LocalShell();
        //shell.executeCommand("node-red");
        //try {
        //    Thread.sleep(10000);
        //    shell.stopCommand();
        //} catch (InterruptedException ex) {
        //    Logger.getLogger(AuthController.class.getName())
        //            .log(Level.SEVERE, null, ex);
        //}
        // ----------------------------------------------------------

//        SSHManager instance = new SSHManager("ubuntu-server", "ubuntu-server", "192.168.25.119",
//                "");
//        String errorMessage = instance.connect();
//
//        if (errorMessage != null) {
//            System.out.println(errorMessage);
//        }
//
//        // call sendCommand for each command and the output 
//        //(without prompts) is returned
//        String result = instance.sendCommand("mkdir novo4");
//        // close only after all commands are sent
//        instance.close();
        // ----------------------------------------------------------
        // Testando envio de arquivo via SFTP
//        SSHManager instance = new SSHManager("ubuntu-server", "ubuntu-server",
//                "192.168.25.119",
//                "");
//        instance.connect();
//        instance.sendFile("/tmp/novo_host.txt", "/tmp/novo_host.txt", TransferProtocolType.SFTP);
//        instance.close();
        return "auth/login";
    }

}
