package BitOrderSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// 디비 클래스
public class OrderDb {
  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  final String user = "include_hoany";
  final String pw = "1234";
  final String driver = "oracle.jdbc.driver.OracleDriver";
  final String url = "jdbc:oracle:thin:@3.35.51.147:6006:XE";
  final SimpleDateFormat orderdate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

  // OrderDB 생성자
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
  public void insertMember(String id, String pw, String nickname, String pn, String authority) {
    String sql = "INSERT INTO MEMBERMANAGER(MMSQ, ID, PW, NICKNAME, PHONE, AUTHORITY) "
        + "VALUES(MMSQ.nextval, ?, ?, ?, ?, ?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, pw);
      pstmt.setString(3, nickname);
      pstmt.setString(4, pn);
      pstmt.setString(5, authority);

      if(pstmt.executeUpdate() > 0) {
        if(authority.equals("CONSUMER")) System.out.println("고객 아이디 등록에 성공하였습니다.\n");
        if(authority.equals("STORE")) System.out.println("가게아이디 등록에 성공하였습니다.\n");
      } else {
        System.out.println("가게아이디 등록에 실패하였습니다.");

      } // end if else

    } catch (SQLException e) {
      System.out.println("이미 가입되어있는 아이디 입니다.");

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
  public ArrayList<Integer> searchStoreList() {
    ArrayList<Integer> indexarr = new ArrayList<Integer>();
    String sql = "SELECT * FROM MEMBERMANAGER m, STORE s WHERE m.AUTHORITY =" 
        + "'" + "STORE" + "'" + "and m.MMSQ = s.MMSQ ORDER BY m.MMSQ";
    try {
      pstmt = conn.prepareStatement(sql);
      rs= pstmt.executeQuery();

      System.out.println("\n[가게목록]");
      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("| %-5s\t| %-5s\t| %-20s\t| %-40s\t| %-5s\t|%n","가게번호", "전화번호", "가게명", "주소", "카테고리");
      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");
      while(rs.next()) {
        indexarr.add(rs.getInt("MMSQ"));
        System.out.printf("| %-10d\t| %-10s\t| %-20s\t| %-30s\t| %-10s\t|%n",
            rs.getInt("MMSQ"),
            rs.getString("PHONE"),
            rs.getString("STORENAME"),
            rs.getString("ADDRESS"),
            rs.getString("CATEGORY"));

      } // end while

      System.out.println(" -------------------------------------------------------------------------------------------------------------------------------");

      return indexarr;
    } catch (SQLException e) {
      e.printStackTrace();

    } // end try / catch

    return indexarr;

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

  //가장 최근에 입력된 회원번호를 찾아오는 메소드.
  public int getLastMMSQ() {
    String sql = "SELECT MMSQ.currval FROM dual";
    int temp = 0;
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        temp = rs.getInt("CURRVAL");

      }

      return temp;

    } catch (SQLException sqle) {
      sqle.printStackTrace();

    } // end try / catch

    return temp;

  } // end Method getLastMMSQ

  //가장 최근에 주문된 주문번호를 찾는 메소드.
  public int getLastODSQ() {
    String sql = "SELECT ODSQ.currval FROM dual";
    int temp = 0;
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        temp = rs.getInt("CURRVAL");

      }

      return temp;

    } catch (SQLException sqle) {
      sqle.printStackTrace();

    } // end try / catch

    return temp;

  } // end Method getLastODSQ

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

  //가게 주인의 메뉴를 반환하는 메서드
  public String[] showMenuNames(int mmsq) { 
    String sql="select foodname from storemenu where mmsq=?";
    ArrayList<String> menuNames=new ArrayList<String>();

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      rs=pstmt.executeQuery();

      while (rs.next())  menuNames.add(rs.getString("foodname")); // end while
    } catch (SQLException e) {
      System.out.println("오류 발생~!");

    } // end try / catch

    return menuNames.toArray(new String[menuNames.size()]);
  } // end Method showMenuNames

  //가게 주인이 자기의 메뉴만을 조회하는 메서드
  public void showMenu() { 
    int mmsq=LoginSession.mmsq;
    String sql="select * from storemenu where mmsq=?";

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      rs=pstmt.executeQuery();

      System.out.println("                            [메뉴목록]                            ");
      System.out.println("----------------------------------------------------------------------");
      System.out.printf("%-5s\t %-30s\t %-4s\n","가게번호","음식 이름","가격");
      System.out.println("----------------------------------------------------------------------");

      while (rs.next()) {
        String foodName=rs.getString("foodname");
        int foodPrice=rs.getInt("price");
        System.out.printf("%-10d\t %-30s\t %-8d\n",mmsq,foodName,foodPrice);

      } // end while

      System.out.println("----------------------------------------------------------------------");
    } catch (SQLException e) {
      System.out.println("오류 발생~");

    } // end try / catch
  }

  //가게 메뉴를 등록하는 메서드
  public void registerMenu(String foodName, int foodPrice) { 
    String sql="insert into storemenu (mmsq,foodname,price) values(?,?,?)";

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, LoginSession.mmsq);
      pstmt.setString(2, foodName);
      pstmt.setInt(3, foodPrice);

      if (pstmt.executeUpdate()>0) 
        System.out.println("가게 메뉴 등록 완료!");
      else System.out.println("가게 메뉴 등록 실패!");  

    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method registerMenu

  //음식이름과 가격을 수정하는 메서드
  public void alterMenu(String prevFoodName, String foodName, int foodPrice) { 
    String sql="update storemenu set foodname=?, price=? where mmsq=? and foodname=?";

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1,foodName);
      pstmt.setInt(2, foodPrice);
      pstmt.setInt(3, LoginSession.mmsq);
      pstmt.setString(4, prevFoodName);

      if (pstmt.executeUpdate() > 0)
        System.out.println("메뉴 수정 완료!!");
      else System.out.println("메뉴 수정 실패!!");

    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method alterMenu

  //가게 소유자의 주문 내역을 조회하는 메서드
  public void showOrderManager() { 
    int mmsq=LoginSession.mmsq;
    String sql="select * from ordermanager where mmsq=?";

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      rs=pstmt.executeQuery();

      System.out.println("                                   [주문 목록]                                   ");
      System.out.println("----------------------------------------------------------------------------------------");
      System.out.printf("%-5s\t %-5s\t %-10s\t %-5s\t %-10s\n","주문번호","가게번호","주문자", "주문상태", "주문날짜");
      System.out.println("----------------------------------------------------------------------------------------");

      while (rs.next()) {
        int orderNum=rs.getInt("odsq");
        String conId=rs.getString("consumerid");
        String orderStatus=rs.getString("status");
        String orderDate=rs.getString("orderdate");
        System.out.printf("%-10d\t %-10d\t %-14s\t %-5s\t %-20s\n",orderNum,mmsq,conId,orderStatus,orderDate);
      } // end while

      System.out.println("----------------------------------------------------------------------------------------");

    } catch (SQLException e) {
      System.out.println("오류발생");;

    } // end try / catch

  } // end Method showOrderManager

  //주문 상태를 처리하는 메서드
  public void handleOrder(int orderNum,String orderMsg) { 
    try {
      String sql="update ordermanager set status=? where odsq=?";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, orderMsg);
      pstmt.setInt(2, orderNum);

      if (pstmt.executeUpdate() >0) {
        System.out.println("주문 처리 완료!");
      } else {
        System.out.println("주문 처리 실패");
      }
    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method handleOrder

  //공지 등록 메서드
  public void registerNotice(String notice) { 
    String sql="update store set notice=? where mmsq=?";

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, notice);
      pstmt.setInt(2, LoginSession.mmsq);

      if (pstmt.executeUpdate()>0) {
        System.out.println("공지 등록 완료~!");
      } else System.out.println("공지 등록 실패~"); // end if / else
    } catch (SQLException e) {
      System.out.println("오류 발생!");

    } // end try / catch

  }

  public Integer[] getOrderNum() {
    String sql="select odsq from ordermanager where mmsq=?";    
    ArrayList<Integer> tmp=new ArrayList<Integer>();
    Integer[] arrOrderNum=null;

    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, LoginSession.mmsq);
      rs=pstmt.executeQuery();

      while (rs.next()) {
        tmp.add(rs.getInt("odsq"));
      } // end while

      arrOrderNum=new Integer[tmp.size()];

      for (int i=0;i<tmp.size();i++) 
        arrOrderNum[i]=tmp.get(i);

    } catch (SQLException e) {
      System.out.println("오류발생");
    } // end try / catch

    return arrOrderNum;
  }

  // 소비자가 리뷰를 등록하는 메소드
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
      System.out.println("오류발생");

    }  // end try / catch 

  } // end Method enrollReviewdb

  // 소비자가 해당 가게에 리뷰를 확인하는 메소드
  public void showReview(int mmsq) {
    String sql = "SELECT * FROM REVIEW WHERE MMSQ=?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      rs = pstmt.executeQuery();
      System.out.println("[리뷰]");
      System.out.println(" ---------------------------------------------------------------------------------------");
      System.out.printf("| %-10s\t| %-45s\t| %-9s|%n", "닉네임", "리뷰내용", "리뷰작성일");
      System.out.println(" ---------------------------------------------------------------------------------------");
      while(rs.next()) {
        System.out.printf("| %-10s\t| %-40s\t| %-11s\t|%n", rs.getString("NICKNAME"), rs.getString("REVIEWCOMMENT"), rs.getDate("CREATEDDATE"));
      }
      System.out.println(" ---------------------------------------------------------------------------------------");
    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method showReview

  // 소비자가 나의 주문 목록을 확인하는 메소드
  public void showMyOrder() {
    String sql = "select ODSQ, STORENAME,  CONSUMERID, STATUS, ORDERDATE from ORDERMANAGER m, STORE s where m.MMSQ = s.MMSQ AND CONSUMERID=? ORDER BY  ORDERDATE DESC";
    try {
      pstmt= conn.prepareStatement(sql);
      pstmt.setString(1, LoginSession.id);
      rs = pstmt.executeQuery();
      System.out.println("\n[주문상태확인]");
      System.out.println(" -----------------------------------------------------------------------------------------------------------------------");
      System.out.printf("| %-5s\t| %-10s\t| %-10s\t| %-10s\t| %-34s|%n", "주문번호", "가게이름", "주문자", "주문상태", "주문시간");
      System.out.println(" -----------------------------------------------------------------------------------------------------------------------");
      while(rs.next()) {
        System.out.printf("|%-10d\t| %-10s\t| %-10s\t| %-15s\t| %-30s\t|%n", rs.getInt("ODSQ"), rs.getString("STORENAME"), rs.getString("CONSUMERID"), rs.getString("STATUS"), orderdate.format(rs.getTimestamp("ORDERDATE")));
      }
      System.out.println(" -----------------------------------------------------------------------------------------------------------------------");
    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch
  }

  // 해당 가게의 메뉴를 출력하는 메소드
  public void showMenuList(int mmsq) {
    String sql = "SELECT * FROM STOREMENU WHERE MMSQ=?";
    try {
      pstmt= conn.prepareStatement(sql);
      pstmt.setInt(1, mmsq);
      rs = pstmt.executeQuery();
      int i = 1;
      while(rs.next()) {
        System.out.printf("%d %s %d%n%n", i++, rs.getString("FOODNAME"), rs.getInt("PRICE"));
      }
    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method showMenuList

  // 주문접수와 주문접수번호를 발급받는 메소드
  public void getReceiptNumber(int storeNumber) {
    String sql = "INSERT INTO ORDERMANAGER(ODSQ, MMSQ, CONSUMERID, STATUS, ORDERDATE) "
        + "VALUES(ODSQ.nextval, ?, ?, ?, SYSDATE)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, storeNumber);
      pstmt.setString(2, LoginSession.id);
      pstmt.setString(3, "접수중");
      if(pstmt.executeUpdate() > 0) {
        System.out.println("접수번호가 발급 되었습니다.");
      } else {
        System.out.println("접수번호 발급 실패...");
      }

    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method getReceiptNumber

  // 주문의 상세 정보를 입력하는 메소드
  public void insertOrderDetail(int odsq, int mmsq, String foodName) {
    String sql = "INSERT INTO ORDERDETAIL(ODSQ, MMSQ, FOODNAME) VALUES(?, ?, ?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, odsq);
      pstmt.setInt(2, mmsq);
      pstmt.setString(3, foodName);
      if(pstmt.executeUpdate() > 0) {
        System.out.println("메뉴가 추가되었습니다.");
      } else {
        System.out.println("메뉴 추가에 실패하였습니다.");
      }
    } catch(SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method insertOrderDetail

  //고객 탈퇴시 멤버 매니저 테이블에서 삭제하는 메소드
  public void deleteMember() {
    String sql = "DELETE FROM MEMBERMANAGER WHERE MMSQ=?";
    try {
      pstmt= conn.prepareStatement(sql);
      pstmt.setInt(1, LoginSession.mmsq);
      if(pstmt.executeUpdate() > 0) {
        System.out.println("탈퇴가 정상적으로 되었습니다.\n");
      } else {
        System.out.println("탈퇴에 실패하였습니다.\n");
      }
    } catch (SQLException e) {
      System.out.println("오류발생");

    } // end try / catch

  } // end Method deleteMember

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
