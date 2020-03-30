package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Test extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get quote van pagina, set quote
        String status = "";
        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
            Person person = (Person) request.getSession().getAttribute("user");
            person.setStatus(status);
        }
        // stuur deze status als json naar html
        if (request.getSession().getAttribute("user") != null) status = ((Person) request.getSession().getAttribute("user")).getStatus();
        String statusJSON = this.toJSON(status);
        response.setContentType("text/json");
        response.getWriter().write(statusJSON);
        return null;//todo return "chatPage.jsp"?
    }

    private String toJSON (String status) {
        StringBuffer json = new StringBuffer();

        json.append("{ \"status\" : \"");
        json.append(status);
        json.append("\"}");
        return json.toString();
    }
}
