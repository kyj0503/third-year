package miniProject;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/numberGuess")
public class NumberGuessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserDAO dao = null;
    
    public NumberGuessServlet() {
        super();
    }

    public void init() throws ServletException {
        super.init();
        dao = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        Integer targetNumber = (Integer) session.getAttribute("targetNumber");
        Integer attempts = (Integer) session.getAttribute("attempts");
        Integer score = (Integer) session.getAttribute("score");

        //타겟 숫자 랜덤 생성  
        if (targetNumber == null || attempts == null || score == null) {
            targetNumber = (int) (Math.random() * 500) + 1;
            attempts = 0;
            score = 500; // 내점수 설정  --> 이 점수를 hp로 해야될 듯?
            session.setAttribute("targetNumber", targetNumber);
            session.setAttribute("attempts", attempts);
            session.setAttribute("score", score);
        }

        String guessParam = request.getParameter("guess");
        String message = "";
        String hint = "";
        String messageClass = "";
        
        if (guessParam != null) {
            int userGuess = Integer.parseInt(guessParam);

            if (userGuess < 1 || userGuess > 500) {
                message = "오류: 1에서 500 사이의 숫자를 입력하세요.";
                messageClass = "error";
            } else {
                attempts++;
                score -= 10;  // 각 시도마다 틀리면 10점씩 차감

                int difference = Math.abs(userGuess - targetNumber);
                if (difference >= 100) {
                    hint = "오늘 안에 찾겠어요~~?";
                } else if (difference >= 50) {
                    hint = "아 언제 찾으실꺼에요 ㅠ";
                } else if (difference >= 25) {
                    hint = "오 좀 가까운데요..?";
                } else {
                    hint = "이걸 맞춰..?";
                }

                if (userGuess < targetNumber) {
                    message = "더 높음";
                    messageClass = "high";
                } else if (userGuess > targetNumber) {
                    message = "더 낮음";
                    messageClass = "low";
                } else {
                    message = "정답! 시도 횟수: " + attempts + ", 점수: " + score;
                    messageClass = "correct";
                    User loggedInUser = (User) session.getAttribute("loggedInUser");
                    if (loggedInUser != null) {
                        loggedInUser.setGame3hp(score); // 최종 점수를 game3hp에 저장
                        dao.updateUser(loggedInUser);
                    }
                    session.removeAttribute("targetNumber");
                    session.removeAttribute("attempts");
                    session.removeAttribute("score");
                }

                session.setAttribute("attempts", attempts);
                session.setAttribute("score", score);
            }
        }

        request.setAttribute("message", message);
        request.setAttribute("hint", hint);
        request.setAttribute("messageClass", messageClass);
        request.getRequestDispatcher("numberGuess.jsp").forward(request, response);
    }
}