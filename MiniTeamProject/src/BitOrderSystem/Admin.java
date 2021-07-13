package BitOrderSystem;

import java.util.Scanner;

public class Admin {
	OrderDb db = null;
	Scanner sc = null;
	
	Admin() {
		this.db = BitOrderSystem.db;
		this.sc = BitOrderSystem.sc;
	}
	
	public void process() {
		System.out.println("[관리자 메뉴]");
		System.out.print("[1. 가게등록  2. 가게수정]");
	}
	
	
	public void enrollStore() {
		System.out.print("아이디 >> ");
		String id = sc.nextLine().trim();
		System.out.print("패스워드 >> ");
		String pw = sc.nextLine().trim();
		System.out.print("닉네임 >> ");
		String nickname = sc.nextLine().trim();
		System.out.print("전화번호 >> ");
		String pn = sc.nextLine().trim();
		db.insertStoreMember(id, pw, nickname, pn);	
	}

}
