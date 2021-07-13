package BitOrderSystem;

import java.util.Scanner;

public class BitOrderSystem {
	static Scanner sc = new Scanner(System.in);
	static OrderDb db = new OrderDb();
	static Admin admin = new Admin();
	static Consumer consumer = new Consumer();
	
	public static void main(String[] args) {
		int index = 9;
		System.out.println("[방문포장 시스템]");
		System.out.print("[1. 로그인	2.회원가입	 3.로그아웃  9.종료 >>");
		index = Integer.parseInt(sc.nextLine());
		switch(index) {
			case 1:
			login();
			selectMenu();
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
	
	
	public static void login() {
		System.out.print("아이디 >> ");
		String id = sc.nextLine().trim();
		System.out.print("패스워드 >> ");
		String pw = sc.nextLine().trim();
		db.loginsql(id, pw);
		switch(LoginSession.authority) {
		case"ADMIN":
			System.out.println("로그인성공 관리자 계정입니다.");
			break;
		case"STORE":
			System.out.println("로그인성공 가게 계정입니다.");
			break;
		case"CON":
			System.out.println("로그인성공 소비자 계정입니다.");
			break;
		}
	}
	
	public static void selectMenu() {
		switch(LoginSession.authority) {
			case "ADMIN" :
			admin.process();
			break;
			case "STORE" :
			break;
			case "CONSUMER" :
			consumer.process();
			break;
		}
	}
	
	public static void logout() {
		LoginSession.mmsq = 0;
		LoginSession.id = null;
		LoginSession.nickname = null;
		LoginSession.authority = null;
		LoginSession.isLogin = false;
	}

}
