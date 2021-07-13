package BitOrderSystem;

import java.util.Scanner;

public class Consumer {
	OrderDb db = null;
	Scanner sc = null;

	Consumer() {
		this.db = BitOrderSystem.db;
		this.sc = BitOrderSystem.sc;
	}
	
	public void process() {
		System.out.println("[소비자 메뉴]");
		System.out.print("[ 1. 주문  2. 주문상태확인 3. 리뷰등록 ]");
	}
}
