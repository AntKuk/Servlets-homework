package com.netcracker.servlets.second;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class ValidationServlet extends HttpServlet {
    final private String validPassword = "qwerty";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String clientPassword = req.getParameter("password");
        String clientLogin = req.getParameter("login");

        out.print(buildResultString(isValid(clientLogin, clientPassword)));
    }

    private String isValid(String login, String password) {

        String result = new String("<h1>Wrong password!!!</h1>\n");

        if(!login.matches("[0-9]+")) {
            result += "Login must contain only numbers";
        }
        else if(validPassword.equals(password)) {
            result = new String("<h1>Correct password!!!</h1>\n");
        }
        return result;
    }

    private String buildResultString(String str) {
        String beginning = new String("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>MyFirstServlet</title>\n" +
                "</head>\n" +
                "<body>\n");
        String ending = new String("</body>\n" +
                "</html>");

        String result = beginning + str + ending;
        return result;
    }
}
