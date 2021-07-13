package BitOrderSystem;

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
		System.out.print("[ 1. 주문  2. 주문상태확인 3. 리뷰등록 4. 리뷰보기 ]");
		int index = Integer.parseInt(sc.nextLine());
		switch(index) {
			case 1:
			    //주문 메소드 작동
				break;
			case 2:
			    //주문상태확인 메소드 작동
				break;
		}
	}
}
