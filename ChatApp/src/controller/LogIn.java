package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String> errors = new ArrayList<>();
		
		String email = request.getParameter("email");
		if (email == null || email.trim().isEmpty()) errors.add("No email given");
		
		String password = request.getParameter("password");
		if (password == null || password.trim().isEmpty()) errors.add("No password given");
		
		if (errors.size() == 0) {
			PersonService personService = super.getPersonService();
			Person person = personService.getAuthenticatedUser(email, password);
			if (person != null) {
				createSession(person, request, response);
				return "chatPage.jsp";
			}
			else errors.add("No valid email/password");
		}
		request.setAttribute("errors", errors);
		return "index.jsp";
	}
	
	private void createSession(Person person, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("user", person);
	}
}
