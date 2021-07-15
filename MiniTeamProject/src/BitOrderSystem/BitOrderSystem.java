package BitOrderSystem;

import java.util.Scanner;

public class BitOrderSystem {
  static Scanner sc = new Scanner(System.in);
  static OrderDb db = new OrderDb();
  static Admin admin = new Admin();
  static Consumer consumer = new Consumer();
  static Owner owner = new Owner();

  // BitOrderSystem 메인 메소드
  public static void main(String[] args) {
    int index = 9;
    System.out.println("[방문포장 시스템]");
    while(true) {
      try {
        System.out.print("[1.로그인 2.회원가입 9.종료] >> ");
        index = Integer.parseInt(sc.nextLine());
        switch(index) {
          case 1:
            // 메인 메소드가 속한 BitOrderSystem 클래스에 있는 login함수 호출
            login();
            break;
          case 2:
            consumer.enrollConsumer();
            break;
          case 9:
            System.out.print("\n방문포장 시스템 종료");
            db.closeDb();
            System.exit(1);
          default:
            System.out.println("잘못된 입력값 입니다.");

        } // end switch

      } catch(NumberFormatException nfe) {
        System.out.println("숫자만 입력해 주세요.");

      } catch(Exception e) {
        System.out.println("알수없는 에러..");

      } // end try / catch

    } // end while

  } // end main

  // ID와 PW를 입력받아 데이터베이스에 확인하고 ID와 PW가 맞다면 로그인 하는 메소드
  public static void login() {
    System.out.print("아이디 >> ");
    String id = sc.nextLine().trim();
    System.out.print("패스워드 >> ");
    String pw = sc.nextLine().trim();

    // id, pw 를 입력받아 orderDB 클래스에 속한 loginsql함수를 호출
    db.loginsql(id, pw);

    // LoginSession.authority 변수에 값을 읽어와 SWITCH로 분기한다.
    try {    
      switch(LoginSession.authority) {
        case "ADMIN":
          System.out.println("로그인성공 관리자 계정입니다.\n");
          admin.process();
          break;
        case "STORE":
          System.out.println("로그인성공 가게 계정입니다.\n");
          owner.process();
          break;
        case "CONSUMER":
          System.out.println("로그인성공 소비자 계정입니다.\n");
          consumer.process();
          break;
      } // end switch

    } catch(NullPointerException npe) {
      System.out.println("아이디 또는 패스워드가 맞지 않습니다.");

    } // end try / catch

  } // end Method login 

} // end Class BitOrderSystem
