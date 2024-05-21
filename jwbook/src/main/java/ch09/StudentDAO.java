package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void close() {
	    try {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void insert(Student s) {
	    open(); // 여기서 open() 메서드 호출
	    String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, s.getUsername());
	        pstmt.setString(2, s.getUniv());
	        pstmt.setDate(3, s.getBirth());
	        pstmt.setString(4, s.getEmail());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	}
	
	public List<Student> getAll() {
		open();
		List<Student> students = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername("username");
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch (Exception e) { e.printStackTrace(); }
		  finally { close(); }
		return students;
	}
	
	// 학생 삭제
	public void delete(int id) throws SQLException {
		open();
		String sql = "DELETE FROM student where id=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		if(pstmt.executeUpdate() == 0) {
			throw new SQLException("DB 에러");
		};
		
		close();
	}
}
	










