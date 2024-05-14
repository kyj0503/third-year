package ch09;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/studentControl")
public class StudentController {
	private static final long serialVerionUID = 1L;
	
	StudentDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		dao = new StudentDAO();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";
		if(request.getParameter("action") == null ) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response); break;
			case "insert": view = insert(request, response); break;
			}
			
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);
		}
	}

	public String list(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

}
