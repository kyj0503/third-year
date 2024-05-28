package miniProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/userControl")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO dao = null;

    public UserController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new UserDAO();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = dao.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("miniProject/userList.jsp").forward(request, response);
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            doGet(request, response); // list 액션이나 액션이 지정되지 않은 경우 doGet 호출
            return;
        }

        // 나머지 액션에 대한 처리
        switch (action) {
            case "login":
                try {
                    String view = login(request, response);
                    getServletContext().getRequestDispatcher("/" + view).forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "join":
                String view = join(request, response);
                getServletContext().getRequestDispatcher("/" + view).forward(request, response);
                break;
        }
    }



    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (dao.validateUser(id, password)) {
            request.setAttribute("message", "로그인 성공!");
            return "miniProject/index.jsp"; // 올바른 경로로 수정
        } else {
            request.setAttribute("message", "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
            return "miniProject/startPage.jsp"; // 경로 수정
        }
    }


    public String list(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", dao.getAll());
        return "miniProject/userList.jsp"; // User 목록을 표시할 JSP 페이지
    }

    public String join(HttpServletRequest request, HttpServletResponse response) {
        User s = new User();
        try {
            BeanUtils.populate(s, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.join(s);
        // Redirect to userList.jsp after successful registration
        return "userControl?action=list"; 
    }


}
