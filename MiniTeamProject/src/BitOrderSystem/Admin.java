package BitOrderSystem;

import java.util.Scanner;

public class Admin {
  OrderDb db = null;
  Scanner sc = null;

  //Admin 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
  Admin() {
    this.db = BitOrderSystem.db;
    this.sc = BitOrderSystem.sc;
  }

  // 관리자 메뉴를 보여주는 메소드
  public void process() {
    System.out.println("[관리자 메뉴]");
    int index = 9;
    adminloop: 
      while(true) {
        System.out.print("[ 1. 가게등록  2. 가게수정  9.로그아웃 ] >> ");
        index = Integer.parseInt(sc.nextLine());
        switch(index) {
          case 1:
            this.enrollStore();
            //가게등록 메소드 작동
            break;
          case 2:
            //가게수정 메소드 작동
            break;
          case 9:
            System.out.println("관리자 로그아웃");
            LoginSession.logout();
            break adminloop;
          default:
            System.out.println("잘못된 입력값 입니다.");
        }
      }

  }

  // public 가게등록 메소드 장동....
  public void enrollStore() {
    System.out.print("아이디 >> ");
    String id = sc.nextLine().trim();
    System.out.print("패스워드 >> ");
    String pw = sc.nextLine().trim();
    System.out.print("닉네임 >> ");
    String nickname = sc.nextLine().trim();
    System.out.print("전화번호 >> ");
    String pn = sc.nextLine().trim();
    System.out.print("가게이름 >> ");
    String storename = sc.nextLine().trim();
    System.out.print("가게주소 >> ");
    String address = sc.nextLine().trim();
    System.out.print("카테고리 >> ");
    String category = sc.nextLine().trim();
    db.insertStoreMember(id, pw, nickname, pn);
    int tempMMSQ = db.searchStoreId(id);
    db.insertStoreTable(tempMMSQ, storename, address, category);
  }

  // public 가게수정 메소드 작동...




}
