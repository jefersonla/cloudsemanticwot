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
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class RemoteShell {

    private final Logger log = LoggerFactory.getLogger(RemoteShell.class);
    private final String host;
    private final String user;
    private final String password;

    public RemoteShell(final String host, final String user,
            final String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public void executeCommand(final String command) throws IOException { // Cliente SSH final
        JSch jsch = new JSch();
        Properties props = new Properties();
        props.put("StrictHostKeyChecking", "no");
        try {
            Session session = jsch.getSession(user, host, 22);
            session.setConfig(props);
            session.setPassword(password);
            session.connect();
            java.util.logging.Logger.getLogger(RemoteShell.class.getName())
                    .log(Level.INFO, session.getServerVersion());

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            // Daqui para baixo é somente para imprimir a saida
            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    System.out
                            .println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();

        } catch (JSchException ex) {
            java.util.logging.Logger.getLogger(RemoteShell.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
