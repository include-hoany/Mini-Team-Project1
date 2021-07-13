package BitOrderSystem;

import java.sql.*;

public class OrderDb {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
  String user = "include_hoany";
  String pw = "1234";
  final String driver = "oracle.jdbc.driver.OracleDriver";
  final String url = "jdbc:oracle:thin:@3.35.51.147:6006:XE";
  
  OrderDb() {
	  try {
		  Class.forName(driver);
		  conn = DriverManager.getConnection(url, user, pw);
		  System.out.println("[방문포장 시스템 데이터베이스 연결 성공]");
	  } catch(SQLException e) {
		  System.out.println("디비연결오류");
	  } catch(ClassNotFoundException e) {
		  System.out.println("드라이버 연결 오류");
	  }
  }
  
  public void insertStoreMember(String id, String pw, String nickname, String pn) {
	  String sql = "INSERT INTO MEMBERMANAGER(MMSQ, ID, PW, NICKNAME, PHONE, AUTHORITY) "
	  		+ "VALUES(MMSQ.nextval, ?, ?, ?, ?, ?)";
	  try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, nickname);
		pstmt.setString(4, pn);
		pstmt.setString(5, "STORE");
		
		if(pstmt.executeUpdate() > 0) {
			System.out.println("가게등록 완료!");
		} else {
			System.out.println("가게등록에 실패하였습니다.");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
  }
  
  
  //매개변수로 ID와 PW를 입력받아 SQL문을 작성후
  // ID와 PW가 일치하는 항목의 MMSQ, ID, NICKNAME, AUTHORITY 값을 받
  // LoginSession 클래스의 스타틱 변수에넣어준다.
  public void loginsql(String id, String pw) {
	  String sql = "SELECT * FROM MEMBERMANAGER WHERE ID=? AND PW=?";
	  try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			LoginSession.mmsq = rs.getInt("MMSQ");
			LoginSession.id = rs.getString("ID");
			LoginSession.nickname = rs.getString("NICKNAME");
			LoginSession.authority = rs.getString("AUTHORITY");
			LoginSession.isLogin = true;		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  
 }
