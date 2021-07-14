package BitOrderSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDb {
  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  final String user = "include_hoany";
  final String pw = "1234";
  final String driver = "oracle.jdbc.driver.OracleDriver";
  final String url = "jdbc:oracle:thin:@3.35.51.147:6006:XE";

  OrderDb() {
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, user, pw);
      System.out.println("[방문포장 시스템 데이터베이스 연결 성공]");

    } catch(SQLException sqle) {
      System.out.println("디비연결오류");

    } catch(ClassNotFoundException cnfe) {
      System.out.println("드라이버 연결 오류");

    } catch(Exception e) {
      System.out.println("알수없는 오류..");

    } // end try / catch

  } // end constructor OrderDb

  // 가게 아이디 데이터베이스 등록 메소드
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
        System.out.println("가게아이디 등록에 성공하였습니다.");
      } else {
        System.out.println("가게아이디 등록에 실패하였습니다.");

      } // end if else

    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

  } // end Method insertStoreMember

  // 가게 아이디 조회 메소드
  public int searchStoreId(String id) {
    String sql = "SELECT * FROM MEMBERMANAGER WHERE id=?";
    int temp = 0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        temp =  rs.getInt("MMSQ");

      } // end While

      return temp;
    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

    return temp;

  } // end Method searchStoreId

  //등록된 가게아이디를 기반으로 가게 테이블 등록
  public void insertStoreTable(int mmsq, String storename, String address, String category) {
    String sql = "INSERT INTO STORE(MMSQ, STORENAME, ADDRESS, CATEGORY, NOTICE) "
        + "VALUES(?, ?, ?, ?, ?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      pstmt.setString(2, storename);
      pstmt.setString(3, address);
      pstmt.setString(4, category);
      pstmt.setString(5, "공지등록안됨");

      if(pstmt.executeUpdate() > 0) {
        System.out.println("가게등록이 완료 되었습니다.");
      } else {
        System.out.println("가게등록에 실패하였습니다.");

      } // end if else

    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

  } // end Method insertStoreTable


  //매개변수로 ID와 PW를 입력받아 SQL문을 작성후
  // ID와 PW가 일치하는 항목의 MMSQ, ID, NICKNAME, AUTHORITY 값을 받는다.
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

      } // end while

    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

  } // end Method loginsql

  // 현재 등록되어 있는 가게 리스트를 출력하는 메소드
  public void searchStoreList() {
    String sql = "SELECT * FROM MEMBERMANAGER m, STORE s WHERE m.AUTHORITY =" 
        + "'" + "STORE" + "'" + "and m.MMSQ = s.MMSQ";
    try {
      pstmt = conn.prepareStatement(sql);
      rs= pstmt.executeQuery();

      System.out.println("\n[가게목록]");
      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("| %-5s\t| %-5s\t| %-20s\t| %-40s\t| %-5s\t|%n","가게번호", "전화번호", "가게명", "주소", "카테고리");
      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");
      while(rs.next()) {
        System.out.printf("| %-10d\t| %-10s\t| %-20s\t| %-30s\t| %-10s\t|%n",
            rs.getInt("MMSQ"),
            rs.getString("PHONE"),
            rs.getString("STORENAME"),
            rs.getString("ADDRESS"),
            rs.getString("CATEGORY"));

      } // end while

      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");

    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

  } // end Method searchStoreList

  // 가게의 속성을 데이터베이스에 갱신하는 메소드
  public void updateStoreMemberTable(int mmsq, String phone) {
    String sql = "UPDATE MEMBERMANAGER SET PHONE=? WHERE MMSQ=?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, phone);
      pstmt.setInt(2, mmsq);
      if(pstmt.executeUpdate() > 0) {}
      else { System.out.println("가게 속성 업데이트 오류.");} // end if else
    } catch (SQLException sqle) {
      sqle.printStackTrace();

    } // end try / catch

  } // end Method updateStoreMemberTable

  // 가게 속성중 STORE테이블의 값을 갱신하는 메소드
  public void updateStoreTable(int mmsq, String storename, String address, String category) {
    String sql = "UPDATE STORE SET STORENAME=?, ADDRESS=?, CATEGORY=? WHERE MMSQ=?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, storename);
      pstmt.setString(2, address);
      pstmt.setString(3, category);
      pstmt.setInt(4, mmsq);
      if(pstmt.executeUpdate() > 0) {}
      else { System.out.println("가게 속성 업데이트 오류.");} // end if else
    } catch(SQLException sqle) {
      sqle.printStackTrace();

    } // end try / catch

  } // end Method updateStoreTable

  public void enrollReviewdb(int mmsq, String reviewComment) {
    String sql = "INSERT INTO REVIEW(MMSQ, NICKNAME, REVIEWCOMMENT, CREATEDDATE) VALUES(?, ?, ?, sysdate)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      pstmt.setString(2, LoginSession.nickname);
      pstmt.setString(3, reviewComment);
      if(pstmt.executeUpdate() > 0) {
        System.out.println("리뷰 등록 완료!");
      } else {
        System.out.println("리뷰 등록에 실패하였습니다.");
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
  }

  // 프로그램 종료시 데이터베이스와의 연결을 해제하는 메소드
  public void closeDb() {
    try {
      if(conn != null) conn.close();
      if(pstmt != null) pstmt.close();
      if(rs != null) rs.close();
    } catch (SQLException sqle) {
      System.out.println("데이터베이스 연결 해제 오류");

    } // end try / catch

  } // end Method closeDb

} // end Class OrderDb
