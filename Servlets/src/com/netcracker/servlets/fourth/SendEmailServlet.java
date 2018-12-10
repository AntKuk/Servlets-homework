package com.netcracker.servlets.fourth;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.*;


public class SendEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ReqParam param = new ReqParam(req);
        User user = new User();

        if (param.isValidEmail()) {

            try {
                MimeMessage message = new MimeMessage(user.getSession());
                message.setFrom(new InternetAddress(user.getUsername()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(param.getTo()));
                if (param.isCc()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(param.getCc()));
                }
                message.setSubject(param.getSubject());
                message.setText(param.getText());
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        } else {
            PrintWriter out = resp.getWriter();
            out.print("Wrong email! Example, example@email.com");
        }

    }
}
