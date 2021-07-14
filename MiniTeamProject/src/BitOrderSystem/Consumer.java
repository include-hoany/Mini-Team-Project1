package BitOrderSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Consumer {
  OrderDb db = null;
  Scanner sc = null;
  static Connection conn = null;

  //Consumer 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
  Consumer() {
    this.db = BitOrderSystem.db;
    this.sc = BitOrderSystem.sc;
  }

  // 소비자 메뉴를 보여주는 메소드
  public void process() {
    System.out.println("[소비자 메뉴]");
    System.out.print("[ 1. 주문  2. 주문상태확인 3. 리뷰등록 4. 리뷰보기 ]");
    int index = Integer.parseInt(sc.nextLine());
    switch(index) {
      case 1:
        //주문 메소드 작동
        Order();
        break;
      case 2:
        //주문상태확인 메소드 작동
        checkOrderStatus();
        break;
      case 3:
        //리뷰등록 메소드 작성
        //enrollReview(null, null, null);
        break;
      case 4:
        //리뷰 보기
        viewReview();
        break;
      default:
        System.out.println("잘못 입력하셨습니다.");
        break;
    }
  }

  public void Order() {
    System.out.println("[음식 종류]\n1. 한식  \n2. 일식 \n3. 중식 \n4. 카페/디저트 ");
    int index = Integer.parseInt(sc.nextLine());
    switch(index) {
      case 1:
        // 한식 판매점 목록1
        break;
      case 2:
        // 일식 판매점 목록
        break;
      case 3:
        //중식 판매점 목록
        break;
      case 4:
        //카페/디저트 목록
        break;
      default:
        System.out.println("잘못 입력하셨습니다.");
        break;

    }

  }


  public static void checkOrderStatus() {
    Connection conn = null;
    String sql = "SELECT * FROM  ";
  }

  public static void enrollReview(String nickname, String reviewComment, String createdDate) {
    PreparedStatement pstmt = null;
    String sql = "INSERT INTO REVIEW(MMSQ, NICKNAME, REVIEWCOMMENT, CREATEDDATE)" + "VALUES(MMSQ.nextval, ?, ?, ?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, nickname);
      pstmt.setString(2, reviewComment);
      pstmt.setString(3, createdDate);

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

  public static void viewReview() {
    String sql = "SELECT * FROM REVIEW WHERE MMSQ LIKE=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        // 모르겠음.... mmsq = rs.mmsq("sql");       
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}