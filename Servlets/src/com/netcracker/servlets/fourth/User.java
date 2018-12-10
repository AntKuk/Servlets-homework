package com.netcracker.servlets.fourth;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class User {
    private final String username = "testservletemail@gmail.com";
    private final String password = "BjKgZS6FTW";

    private final String host = "smtp.gmail.com";
    private final String port = "587";

    private Properties properties = new Properties();
    private Session session;

    public User() {
        setProperties();
    }

    public Session getSession() {
        this.session = Session.getDefaultInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    private void setProperties() {
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.host", host);
        this.properties.put("mail.smtp.port", port);
        this.properties.put("mail.smtp.starttls.enable", "true");
    }

    public String getUsername() {
        return username;
    }
}
