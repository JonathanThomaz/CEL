/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.sun.mail.pop3.POP3SSLStore;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import static jdk.nashorn.internal.codegen.OptimisticTypesPersistence.store;

/**
 *
 * @author admin
 */
public class TesteChecar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String host = "pop.gmail.com";
        final String username = "jonathanthomaz96@gmail.com";
        final String password = "FutbolNathan1256";

        try {
            Properties props = new Properties();
            /**
             * Parâmetros de conexão com servidor
             */
            props.put("mail.smtp.host", "smtp");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.pop3.host", "pop3");
            props.put("mail.pop3.socketFactory.port", "995");
            props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.pop3.auth", "true");
            props.put("mail.pop3.port", "995");

            // Get session
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //session.setDebug(true);
            // Get the store
            System.out.println("Session complete");

            Store store = session.getStore("pop3");
            System.out.println("Store complete");
          //store.connect(host, username, password);

            URLName url = new URLName("pop3","pop.gmail.com",995,"",username,password);
            store = new POP3SSLStore(session, url);
            store.connect();
            System.out.println(store.isConnected());

            // Get folder
            Folder folder = store.getDefaultFolder();
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            // Get directory
            Message message[] = folder.getMessages();
            
            for (int i = 0, n = message.length; i < n; i++) {
                System.out.println(i + ": " + message[i].getFrom()[0]
                        + "\t" + message[i].getSubject());
            }

            // Close connection 
            folder.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
