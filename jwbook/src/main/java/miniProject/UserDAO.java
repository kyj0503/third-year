package miniProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection conn = null; // DB 연결 담당 객체
    PreparedStatement pstmt; // SQL 문장 담기 위한
    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test"; // 수정된 URL
    
    public void open() {
        try {
            // Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            // Establish the database connection
            conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
            System.out.println("DB 연결 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC 드라이버 로드 실패");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
    }


    public void close() {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("DB Connection is Closed!!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB Connection 해제 실패: " + e.getMessage());
        }
    }

    
    public void join(User s) {
        open();
        if (conn == null) {
            System.out.println("DB 연결에 실패하여 join 메서드를 종료합니다.");
            return;
        }
        String sql = "INSERT INTO users(id, password, name, game1hp, game2hp) VALUES (?, ?, ?, 1000, 1000)"; // game1hp와 game2hp의 기본값을 1000으로 설정
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getId());
            pstmt.setString(2, s.getPassword()); // 비밀번호 추가
            pstmt.setString(3, s.getName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    
    public boolean validateUser(String id, String password) throws SQLException {
        open();
        if (conn == null) {
            System.out.println("DB 연결에 실패하여 validateUser 메서드를 종료합니다.");
            return false;
        }
        String sql = "SELECT id FROM users WHERE id = ? AND password = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, password);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // 사용자 존재하는 경우
                System.out.println("User " + id + " authenticated.");
                return true;
            } else {
                // 사용자 존재하지 않는 경우
                System.out.println("User " + id + " authentication failed.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }
    
    public List<User> getAll() {
        open();
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, game1hp, game2hp FROM users";
        try {
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setGame1hp(rs.getInt("game1hp"));
                user.setGame2hp(rs.getInt("game2hp"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return users;
    }
    
 // Add this method to fetch user by ID
    public User getUserById(String id) {
        open();
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setGame1hp(rs.getInt("game1hp"));
                user.setGame2hp(rs.getInt("game2hp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return user;
    }

    // Add this method to update user details
    public void updateUser(User user) {
        open();
        String query = "UPDATE users SET game1hp = ?, game2hp = ? WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, user.getGame1hp());
            pstmt.setInt(2, user.getGame2hp());
            pstmt.setString(3, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


}
