개발환경

- 이클립스 2023-12
  - Java17
  - Tomcat Server 10.1
- H2 Database

-----------------------------------------------------------------------

src

- java

  - miniProject
  
    - User.java  :  DTO 자바 코드
    - UserDAO.java  :  DAO 자바 코드
      
    - UserController  :  컨트롤러 ( 가위바위보 게임용 서블릿 포함 )
    - GameController.java  :  오목 게임용 서블릿
    - NumberGuessServlet.java  :  숫자 맞추기 게임용 서블릿

    - GameModel.java  :  가위바위보 로직
      
- webapp

  - startPage.jsp  :  로그인 화면
  - join.jsp  :  회원가입 화면
  - userList.jsp  :  회원목록 화면
    
  - index.jsp  :  게임 선택 화면
  - GameView.jsp  :  가위바위보 게임 화면
  - ResultView.jsp  :  가위바위보 결과 화면
  - Gomoku.jsp  :  오목 게임 화면
  - numberGuess.jsp  :  숫자 맞추기 게임 화면
    
