package BitOrderSystem;

import java.util.ArrayList;
import java.util.Scanner;

// 고객 클래스
public class Consumer {
  OrderDb db = null;
  Scanner sc = null;

  //Consumer 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
  Consumer() {
    this.db = BitOrderSystem.db;
    this.sc = BitOrderSystem.sc;

  } // end Constructor Consumer

  // 소비자 메뉴를 보여주는 메소드
  public void process() {
    System.out.println("[고객 메뉴]");
    try {
      loop:
        while(true) {
          System.out.print("[ 1. 주문  2. 주문상태확인 3. 리뷰등록 4. 리뷰보기 5. 회원탈퇴 9. 로그아웃 ] >> ");
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
            case 5:
              membershipWithdrawal();
              LoginSession.logout();
              break loop;
            case 9:
              // 로그아웃
              LoginSession.logout();
              break loop;
            default:
              System.out.println("잘못된 번호를 입력하셨습니다.");
              break;

          } // end switch

        } // end while

    } catch(NumberFormatException nfe) {
      System.out.println("숫자만 입력해 주세요..");

    } catch (Exception e) {
      System.out.println("알수없는 에러..");
      e.printStackTrace();

    } // end try / catch

  } // end Method process

  //고객이 주문을 접수하는 메소드
  public void Order() {
    ArrayList<Integer> indexarr = db.searchStoreList();
    System.out.print("주문할 가게번호를 입력해주세요. >> ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    if(!indexarr.contains(storeNumber)) {
      System.out.println("잘못된 가게번호를 입력 하셨습니다.");
      return;
    }
    String[] menuNames = db.showMenuNames(storeNumber);
    if(menuNames.length == 0) {
      System.out.println("가게에 등록되어 있는 메뉴가 존재하지 않습니다. 주문을 종료합니다.");
      return;
    }
    System.out.println("[메뉴리스트]");
    for (int i=0;i<menuNames.length;i++) System.out.print((i+1)+"."+menuNames[i]+"\t");
    System.out.print("\n주문을 접수 하시겠습니까? (y/n) >> ");
    String go = sc.nextLine();
    if(go.equals("n")) return;

    db.getReceiptNumber(storeNumber);
    int odsq = db.getLastODSQ();

    while(true) {
      for (int i=0;i<menuNames.length;i++) System.out.print((i+1)+"."+menuNames[i]+"\t");
      System.out.print("\n주문할 메뉴 번호를 입력하세요. >> ");
      int menuNumber = Integer.parseInt(sc.nextLine());

      db.insertOrderDetail(odsq, storeNumber, menuNames[menuNumber-1]);

      System.out.print("더 주문하시겠습니까? (y/n) >> ");
      String choice = sc.nextLine();

      if(choice.equals("n")) break;
    }

    System.out.println("주문이 완료되었습니다.");

  } // end Method Order

  // 고객이 리뷰를 작성하는 메소드
  public void enrollReview() {
    ArrayList<Integer> indexarr = db.searchStoreList();
    System.out.print("리뷰를 등록할 가게번호를 입력해주세요. >> ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    if(!indexarr.contains(storeNumber)) {
      System.out.println("잘못된 가게번호를 입력 하셨습니다. >> ");
      return;
    }
    System.out.print("리뷰 내용을 작성하세요 >> ");
    String comment = sc.nextLine();
    db.enrollReviewdb(storeNumber, comment);

  } // end Method enrollReview

  // 고객이 리뷰를 확인하는 메소드
  public void viewReview() {
    ArrayList<Integer> indexarr = db.searchStoreList();
    System.out.print("리뷰를 확인할 가게번호를 입력해주세요. >> ");
    int storeNumber = Integer.parseInt(sc.nextLine());
    if(!indexarr.contains(storeNumber)) {
      System.out.println("잘못된 가게번호를 입력 하셨습니다. >> ");
      return;
    }
    db.showReview(storeNumber);

  } // Method viewReview


  // 고객이 요청한 주문의 상태를 확인하는 메소드
  public void checkOrderStatus() {
    db.showMyOrder();

  } // end Method checkOrderStatus

  // 고객 회원가입
  public void enrollConsumer() {
    System.out.println("\n[회원가입]");
    System.out.print("아이디 >> ");
    String id = sc.nextLine().trim();
    System.out.print("패스워드 >> ");
    String pw = sc.nextLine().trim();
    System.out.print("닉네임 >> ");
    String nickname = sc.nextLine().trim();
    System.out.print("전화번호 ex)010-0000-0000 >> ");
    String pn = sc.nextLine().trim();

    // 쓰레드 슬립을 사용한 이유는 혹시나 빠르게 디비 연산을 하다보면 돌연사 할까봐...
    try {Thread.sleep(2000);} catch (InterruptedException ic) {}
    db.insertMember(id, pw, nickname, pn, "CONSUMER");

  }

  //고객 회원탈퇴
  public void membershipWithdrawal() {
    System.out.print("정말 탈퇴 하시겠습니까? (y/n) >> ");
    String choice = sc.nextLine();
    if(choice.equals("y")) {
      db.deleteMember(LoginSession.mmsq);
      return;
    }

    System.out.println("탈퇴를 철회하였습니다. >> ");

  } // end Method membershipWithdrawal

} // end Class Consumer
