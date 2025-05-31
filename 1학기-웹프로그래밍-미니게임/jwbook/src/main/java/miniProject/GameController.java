package miniProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
	
	    String player1WinStr = request.getParameter("player1Win");
	    String player1LoseStr = request.getParameter("player1Lose");
	    String player2WinStr = request.getParameter("player2Win");
	    String player2LoseStr = request.getParameter("player2Lose");
	
	    int player1Win = player1WinStr != null ? Integer.parseInt(player1WinStr) : 0;
	    int player1Lose = player1LoseStr != null ? Integer.parseInt(player1LoseStr) : 0;
	    int player2Win = player2WinStr != null ? Integer.parseInt(player2WinStr) : 0;
	    int player2Lose = player2LoseStr != null ? Integer.parseInt(player2LoseStr) : 0;
	
	    System.out.println("Player1 Win: " + player1Win);
	    System.out.println("Player1 Lose: " + player1Lose);
	    System.out.println("Player2 Win: " + player2Win);
	    System.out.println("Player2 Lose: " + player2Lose);
	
	    int currentGame2Hp = loggedInUser.getGame2hp();
	
	    if (player1Win == 1) {
	        currentGame2Hp += 100;
	        System.out.println("Player1 wins. New Game2 HP: " + currentGame2Hp);
	    } else if (player1Lose == 1) {
	        currentGame2Hp -= 100;
	        System.out.println("Player1 loses. New Game2 HP: " + currentGame2Hp);
	    }
	
	    loggedInUser.setGame2hp(currentGame2Hp);
	    dao.updateUser(loggedInUser);
	
	    System.out.println("User updated in DB: " + loggedInUser);
	
	    response.sendRedirect("Gomoku.jsp");
	}
}
