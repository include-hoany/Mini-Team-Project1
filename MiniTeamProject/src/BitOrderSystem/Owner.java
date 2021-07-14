package BitOrderSystem;

import java.util.Scanner;

public class Owner {
  OrderDb db = null;
  Scanner sc = null;

  String[] orderStatus= { "접수중","접수 완료","접수 취소" };

  //Owner 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
  Owner() {
    this.db = BitOrderSystem.db;
    this.sc = BitOrderSystem.sc;
  }

  // 가게 메뉴를 보여주는 메소드
  public void process() {

    while(true) {
      try {
        System.out.println("[가게 메뉴]");
        System.out.print("[ 1. 메뉴등록  2. 메뉴가격수정 3. 주문접수 4. 공지 9. 로그아웃 ]");
        int menuNum=Integer.parseInt(sc.nextLine());

        switch (menuNum) {
          case 1: enrollMenu(); break;
          case 2: modifyMenu(); break;
          case 3: acceptOrder(); break;
          case 4: notice(); break;
          case 9: 
            System.out.println("가게 로그아웃\n");
            LoginSession.logout(); 
            return;
          default :
            System.out.println("잘못 입력하였습니다.");
            break;
        }
      } catch (Exception e) {
        System.out.println("숫자를 입력해주세요.");
      }
    }
  }

  public void enrollMenu() {
    try {
      System.out.print("음식 이름은 무엇입니까 ?>>>");
      String name=sc.nextLine();
      System.out.print("가격은 얼마죠?>>>");
      int price=Integer.parseInt(sc.nextLine());

      // storemenu를 등록하는 db 메서드(name,price);
    } catch (NumberFormatException e) {
      System.out.println("형식에 맞지 않네요~");
    } 
  }

  public void modifyMenu() {
    //storemenu를 보여주는 db 메서드(mmsq)
    System.out.print("바꾸고자 하는 음식 이름을 적어주세요.>>>");
    String name=sc.nextLine();
    System.out.print("바꾸고자 하는 음식의 가격을 적어주세요.>>>");
    int price=Integer.parseInt(sc.nextLine());
    //db.메뉴수정 메서드(name,price)
  }

  public void acceptOrder() {
    try {
      //db.ordermanager를 보여주는 메서드(mmsq)
      System.out.print("주문번호를 입력하세요.>>>");
      int orderNum=Integer.parseInt(sc.nextLine());
      System.out.println();

      for (int i=0;i<orderStatus.length;i++) 
        System.out.print((i+1)+"."+orderStatus[i]+" ");

      System.out.println();
      System.out.print("주문상태를 골라주세요.>>>");
      int num=Integer.parseInt(sc.nextLine());
      String orderMsg=orderStatus[num-1];

      //db.orderManger의 주문상태를 수정하는 메서드(orderNum,orderMsg)
    } catch (Exception e) {
      return;
    }
  }

  public void notice() {
    System.out.print("공지사항을 등록해주세요.>>>");
    String notice=sc.nextLine();

    //db.store 테이블의 공지를 등록하는 메서드(notice)
    //db.store 테이블을 조회하는 메서드.
  }
}
