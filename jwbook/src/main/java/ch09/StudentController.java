package ch09;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/studentControl")
public class StudentController extends HttpServlet{
	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;
	
	StudentDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";
		if(action == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
			return;
		} else {
			switch(action) {
			case "list": view = list(request, response); break;
			case "insert": view = insert(request, response); break;
			case "delete": view = delete(request, response); break;
			}
			
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
	    int id = Integer.parseInt(request.getParameter("id"));
	    try {
	        dao.delete(id);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "학생 정보 삭제 중 오류가 발생했습니다.");
	        return "error.jsp";
	    }
	    return list(request, response);
	}

	// list, add view (studentInfo.jsp)로 이동
	public String list(HttpServletRequest request, HttpServletResponse response) {
		// getAll
		request.setAttribute("students", dao.getAll());
		return "studentInfo.jsp";
	}
	
	// Student 정보 기입 후 studentInfo 보여주기
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
		dao.insert(s);
		return list(request, response);
	}

}
