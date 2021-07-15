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
        try {
          System.out.print("[ 1. 가게등록  2. 가게수정  9.로그아웃 ] >> ");
          index = Integer.parseInt(sc.nextLine());
          switch(index) {
            case 1:
              //가게등록 메소드 작동
              this.enrollStore();
              break;
            case 2:
              //가게수정 메소드 작동
              this.modifyStore();
              break;
            case 9:
              System.out.println("관리자 로그아웃\n");
              LoginSession.logout();
              break adminloop;
            default:
              System.out.println("잘못된 입력값 입니다.");
          }
        } catch(NumberFormatException nfe) {
          System.out.println("숫자만 입력해 주세요.");
        } catch(Exception e) {
          System.out.println("알수없는 에러..");
          e.printStackTrace();
        }
      }

  }

  // 가게를 등록하는 메소드
  public void enrollStore() {
    System.out.print("아이디 >> ");
    String id = sc.nextLine().trim();
    System.out.print("패스워드 >> ");
    String pw = sc.nextLine().trim();
    System.out.print("닉네임 >> ");
    String nickname = sc.nextLine().trim();
    System.out.print("전화번호 ex)010-0000-0000 >> ");
    String pn = sc.nextLine().trim();
    System.out.print("가게이름 >> ");
    String storename = sc.nextLine().trim();
    System.out.print("가게주소 >> ");
    String address = sc.nextLine().trim();
    System.out.print("카테고리 >> ");
    String category = sc.nextLine().trim();

    // 쓰레드 슬립을 사용한 이유는 혹시나 빠르게 디비 연산을 하다보면 돌연사 할까봐...
    try {Thread.sleep(2000);} catch (InterruptedException ic) {}
    db.insertMember(id, pw, nickname, pn, "STORE");

    try {Thread.sleep(2000);} catch (InterruptedException ic) {}

    int tempMMSQ = db.searchStoreId(id);
    try {Thread.sleep(2000);} catch (InterruptedException ic) {}
    db.insertStoreTable(tempMMSQ, storename, address, category);

  }

  // 가게의 속성 정보를 갱식하는 메소드
  public void modifyStore() {
    // 어떤 가게를 수정할지 확인하기 위해
    /// 어떤 가게들이 있는지 확인하고 수정할 가게번호를 찾아 수정한다.
    db.searchStoreList();
    try {
      System.out.print("수정할 가게번호 >> ");
      int mmsq = Integer.parseInt(sc.nextLine().trim());
      System.out.print("수정할 전화번호 >> ");
      String phone = sc.nextLine().trim();
      System.out.print("수정할 가게명 >> ");
      String storename = sc.nextLine().trim();
      System.out.print("수정할 주소 >> ");
      String address = sc.nextLine().trim();
      System.out.print("수정할 카테고리 >> ");
      String category = sc.nextLine().trim();
      db.updateStoreMemberTable(mmsq, phone);
      db.updateStoreTable(mmsq, storename, address, category);

    } catch(NumberFormatException nfe) {
      System.out.println("숫자만 입력해 주세요... ");

    } catch(Exception e) {
      System.out.println("알수없는 에러... ");

    }

  } // end Mothod  modifyStore

}
