// GameController.java
package miniProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/gomokuControl")
public class GameController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO dao = null;

    public GameController() {
        super();
    }

    public void init() throws ServletException {
        super.init();
        dao = new UserDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if (action == null || action.equals("playGomoku")) {
            playGomoku(request, response);
            return;
        }
    }

    private void playGomoku(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser == null) {
            response.sendRedirect("startPage.jsp");
            return;
        }

        int player1Win = Integer.parseInt(request.getParameter("player1Win"));
        int player1Lose = Integer.parseInt(request.getParameter("player1Lose"));
        int player2Win = Integer.parseInt(request.getParameter("player2Win"));
        int player2Lose = Integer.parseInt(request.getParameter("player2Lose"));

        int currentGame2Hp = loggedInUser.getGame2hp();

        if (player1Win == 1) {
            currentGame2Hp += 100;
        } else if (player1Lose == 1) {
            currentGame2Hp -= 100;
        }

        loggedInUser.setGame2hp(currentGame2Hp);
        dao.updateUser(loggedInUser);

        response.sendRedirect("Gomoku.jsp");
    }
}
