package BitOrderSystem;

import java.util.Scanner;

public class BitOrderSystem {
  static Scanner sc = new Scanner(System.in);
  static OrderDb db = new OrderDb();
  static Admin admin = new Admin();
  static Consumer consumer = new Consumer();
  static Owner owner = new Owner();

  public static void main(String[] args) {
    int index = 9;
    System.out.println("[방문포장 시스템]");
    System.out.print("[1. 로그인	2.회원가입	 3.로그아웃  9.종료 >> ");
    index = Integer.parseInt(sc.nextLine());
    switch(index) {
      case 1:
        // 메인 메소드가속한 BitOrderSystem에 있는 login함수 호출
        login();
        // 메인 메소드가속한 BitOrderSystem에 있는 selectMenu함수 호출
        break;
      case 2:
        break;
      case 3:
        logout();
        break;
      case 9:
        System.exit(1);
      default:
        System.out.println("잘못된 입력값 입니다.");			
    }
  }

  // ID와 PW를 입력받아 OrderDB
  public static void login() {
    System.out.print("아이디 >> ");
    String id = sc.nextLine().trim();
    System.out.print("패스워드 >> ");
    String pw = sc.nextLine().trim();

    // id, pw 를 입력받아 orderDB 클래스에 속한 loginsql함수를 호출
    db.loginsql(id, pw);
    // LoginSession.authority 변수에 값을 읽어와 SWITCH로 분기한다.
    switch(LoginSession.authority) {
      case"ADMIN":
        System.out.println("로그인성공 관리자 계정입니다.");
        admin.process();
        break;
      case"STORE":
        System.out.println("로그인성공 가게 계정입니다.");
        owner.process();
        break;
      case"CONSUMER":
        System.out.println("로그인성공 소비자 계정입니다.");
        consumer.process();
        break;
    }
  }

  // 로그아웃 메소드 LoginSession의 스타틱 변수 값을 null, false로 초기화 시킨다.
  public static void logout() {
    LoginSession.mmsq = 0;
    LoginSession.id = null;
    LoginSession.nickname = null;
    LoginSession.authority = null;
    LoginSession.isLogin = false;
  }

}
