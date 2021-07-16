# Mini-Team-Project1
# 주제: 음식 방문포장 주문 시스템
---------------------------------------------------------
## 조원
- 조원: 최환영, 이승철, 박현민
---------------------------------------------------------

## 목적
음식점을 방문하여 주문하고 주문한 음식이 완료될때까지 기다리는 수고스러움을 느낄때가 있습니다.
따라서 음식 방문포장 주문 시스템을 통해 집 또는 사무실에서 음식을 주문하여 음식점에서 주문한 음식이
완료되면 빠르게 찾아갈 수 있는 정보를 제공하고 불필요한 외부 활동을 줄이는데 목적이 있습니다.

---------------------------------------------------------

## 기능
### 메인
- 로그인
- 고객 회원가입
- 종료

### 관리자
- 가게등록
- 가게 정보 수정 삭제
- 로그아웃

### 가게
- 메뉴등록
- 메뉴가격수정
- 주문확인
- 공지
- 로그아웃

### 고객
- 주문
- 주문상태확인
- 리뷰등록
- 리뷰보기
- 회원탈퇴
- 로그아웃
---------------------------------------------------------

## Class And Method 구조
### 메인이 있는 클래스
>class BitOrderSystem
>- public static void main(String[] args)
>- public static void mainProcess()
>- public static void login()

### 관리자 클래스
>class Admin
>- public void process()
>- public void enrollStore()
>- public void modifyStore()

### 고객 클래스
>class Consumer
>- public void process()
>- public void Order()
>- public void enrollReview()
>- public void viewReview()
>- public void checkOrderStatus()
>- public void enrollConsumer()
>- public void membershipWithdrawal()

### 판매자 클래스
>class Owner
>- public void process()
>- public void enrollMenu()
>- public void modifyMenu()
>- public void processOrder()
>- public void notice()

### 디비 관리 클래스
>class OrderDb
>- OrderDb()
>- public void insertMember(String id, String pw, String nickname, String pn, String authority)
>- public int searchStoreId(String id)
>- public void insertStoreTable(int mmsq, String storename, String address, String category)
>- public void loginsql(String id, String pw)
>- public ArrayList<Integer> searchStoreList()
>- public void updateStoreMemberTable(int mmsq, String phone)
>- public int getLastMMSQ()
>- public int getLastODSQ()
>- public void updateStoreTable(int mmsq, String storename, String address, String category)
>- public String[] showMenuNames(int mmsq)
>- public void showMenu()
>- public void registerMenu(String foodName, int foodPrice)
>- public void alterMenu(String prevFoodName, String foodName, int foodPrice)
>- public void showOrderManager()
>- public void handleOrder(int orderNum,String orderMsg)
>- public void registerNotice(String notice)
>- public Integer[] getOrderNum()
>- public void enrollReviewdb(int mmsq, String reviewComment)
>- public void showReview(int mmsq)
>- public void showMyOrder()
>- public void showMenuList(int mmsq)
>- public void getReceiptNumber(int storeNumber)
>- public void insertOrderDetail(int odsq, int mmsq, String foodName)
>- public void deleteMember()
>- public void closeDb()

### 로그인 정보 확인 클래스
>class LoginSession
>- public static void logout()
---------------------------------------------------------

## Table
- MEMBERMANAGER
- STORE
- STOREMENU
- ORDERMANAGER
- ORDERDETAIL
- REVIEW

---------------------------------------------------------

## 역할
- 최환영 Admin 클래스 개발
- 이승철 Owner 클래스 개발
- 박현민 Consumer 클래스 개발

## 공통
- OrderSystem 메인클래스 개발
- 데이터베이스 관리 클래스 개발
- 데이터베이스 테이블 설계
