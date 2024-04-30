package ch08;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/calcControl")
public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CalcController() {
		super();
	}
}
