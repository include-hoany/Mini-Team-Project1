package BitOrderSystem;

import java.util.Scanner;

public class Owner {
	OrderDb db = null;
	Scanner sc = null;
	
	//Owner 생성자에서 BitOrderSystem 클래스의 db, sc 를 가지고 온다.
	Owner() {
		this.db = BitOrderSystem.db;
		this.sc = BitOrderSystem.sc;
	}
	
	// 가게 메뉴를 보여주는 메소드
	public void process() {
		System.out.println("[가게 메뉴]");
		System.out.print("[ 1. 메뉴등록  2. 메뉴가격수정 3. 주문접수 4. 공지 ]");
	}

}
