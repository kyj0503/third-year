package ch06;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/queryTest")
public class QueryTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public QueryTestServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id= request.getParameter("ID");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String [] hobbies = request.getParameterValues("hobby");		
		String gender = request.getParameter("gender");
		String religion = request.getParameter("religion");
		String intro = request.getParameter("introduction");
		
		response.setContentType("text/html;charset=UTF-8");		
		
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>멤버정보</title></head><body>");
		out.print("id: " + id + "<br>");
		out.print("password: " + pwd + "<br>");
		out.print("name: " + name + "<br>");
		out.print("hobbies: ");
		for(int i=0; i < hobbies.length; i++) {
			out.print(hobbies[i] + " ");
		}
		out.print("<br> 성별: " + gender + "<br>");
		out.print("종교: " + religion + "<br>");
		out.print("소개: " + intro + "<br>");
		out.print("전체질의 문자열: " + request.getQueryString() + "<br>");
		out.print("</body></html>");
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
