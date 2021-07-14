package BitOrderSystem;

import java.sql.Connection;
import java.util.Scanner;

public class Consumer {
  OrderDb db = null;
  Scanner sc = null;

  //Consumer 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
  Consumer() {
    this.db = BitOrderSystem.db;
    this.sc = BitOrderSystem.sc;
  }

  // 소비자 메뉴를 보여주는 메소드
  public void process() {
    System.out.println("[소비자 메뉴]");
    loop:
      while(true) {
        System.out.print("[ 1. 주문  2. 주문상태확인 3. 리뷰등록 4. 리뷰보기 9. 로그아웃 ]");
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
            enrollReview();
            break;
          case 4:
            //리뷰 보기
            viewReview();
            break;
          case 9:
            // 로그아웃
            LoginSession.logout();
            break loop;
          default:
            System.out.println("잘못 입력하셨습니다.");
            break;
        }
      }
  }

  public void Order() {
    String[] category = {"한식", "일식", "중식", "패스트푸드"};
    System.out.println("[음식 종류]\n1. 한식  \n2. 일식 \n3. 중식 \n4. 패스트푸드 ");
    int index = Integer.parseInt(sc.nextLine());
    switch(category[index-1]) {
      case "한식":
        // 한식 판매점 목록1
        break;
      case "일식":
        // 일식 판매점 목록
        break;
      case "중식":
        //중식 판매점 목록
        break;
      case "패스트푸드":
        //패스트푸드 목록
        break;
      default:
        System.out.println("잘못 입력하셨습니다.");
        break;
    }

    System.out.print("주문할 가게번호를 입력해주세요. ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    //String[] menu = db.menuarray();
    // 가게번호를 기준으로 헤당 가게에 메뉴리스트를 가지고 온다.

    while(true) {
      System.out.print("주문할 메뉴 번호를 입력하세요. ");
      int menuNumber = Integer.parseInt(sc.nextLine());
      // 데이터 연산처리 예정
      System.out.print("더 주문하시겠습니까? (y/n) ");
      String choice = sc.nextLine();
      if(choice.equals("n")) {
        break;
      }
    }

    System.out.println("주문이 완료되었습니다.");

  }

  public void enrollReview() {
    db.searchStoreList();
    System.out.print("주문할 가게번호를 입력해주세요. ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    System.out.print("리뷰 내용을 작성하세요 ");
    String comment = sc.nextLine();

    // 디비등록
    db.enrollReviewdb(storeNumber, comment);
  }

  public void viewReview() {
    db.searchStoreList();
    System.out.print("리뷰를 확인할 가게번호를 입력해주세요. ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    db.showReview(storeNumber);

  }


  public static void checkOrderStatus() {
    Connection conn = null;
    String sql = "SELECT * FROM  ";
  }




}