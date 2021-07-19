# Mini-Team-Project1

---------------------------------------------------------
## 조원
- 조원: 최환영, 이승철, 박현민

---------------------------------------------------------
## 주제: 음식 방문포장 주문 시스템
음식점을 방문하여 주문하고 주문한 음식이 완료될 때 까지 기다리는 수고스러움을 느낄 때가 있습니다.
따라서 음식 방문 포장 주문 시스템을 통해 집 또는 사무실에서 음식을 주문하여 음식점에서 주문한 음식이
완료되면 빠르게 찾아갈 수 있는 정보를 제공하고 불필요한 외부 활동을 줄일 수 있는 프로그램을 구현하고자 합니다.

---------------------------------------------------------
## 목적
강의내용을 토대로 자바를 통한 데이터베이스 CRUD 구현에 기술적 학습 목적이 있으며, 미니 프로젝트를 진행하며
프로그램 설계 및 구현 시 중요한 팀원 간 커뮤니케이션 능력을 향상하는데 목적이 있습니다.

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
- 메뉴 수정
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
>- Admin()
>- public void process()
>- public void enrollStore()
>- public void modifyStore()
>- public void membershipWithdrawal()

### 고객 클래스
>class Consumer
>- Consumer()
>- public void process()
>- public void Order()
>- public void enrollReview()
>- public void viewReview()
>- public void checkOrderStatus()
>- public void enrollConsumer()
>- public void memberWithdrawal()

### 판매자 클래스
>class Owner
>- Owner()
>- public void process()
>- public void enrollMenu()
>- public void modifyMenu()
>- public void processOrder()
>- public void OrderDetail()
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
>- public ArrayList<String> showMenuNames(int mmsq , boolean show)
>- public void showMenu()
>- public void registerMenu(String foodName, int foodPrice)
>- public void alterMenu(String prevFoodName, String foodName, int foodPrice)
>- public void showOrderManager()
>- public void handleOrder(int orderNum,String orderMsg)
>- public void registerNotice(String notice)
>- public Integer[] getOrderNum()
>- public void enrollReviewdb(int mmsq, String reviewComment)
>- public void showReview(int mmsq)
>- public ArrayList<Integer> showMyOrder()
>- public void showMyOrderDetail(int odsq)
>- public void showMenuList(int mmsq)
>- public void getReceiptNumber(int storeNumber)
>- public void insertOrderDetail(int odsq, int mmsq, String foodName)
>- public void deleteMember(int mmsq)
>- public int getPriceSum(int odsq)
>- public String getNotice(int storeNum)
>- public void closeDb()

### 로그인 정보 확인 클래스
>class LoginSession
>- public static void logout()

---------------------------------------------------------

## Table 구조
>MEMBERMANAGER
>- CREATE TABLE MEMBERMANAGER(
>-  MMSQ NUMBER(4) PRIMARY KEY ,
>-  ID VARCHAR2(20) CONSTRAINT IDUQ UNIQUE,
>-  PW VARCHAR2(20) NOT NULL,
>-  NICKNAME VARCHAR2(20) NOT NULL CONSTRAINT NICK UNIQUE,
>-  PHONE VARCHAR2(13) NOT NULL,
>-  AUTHORITY VARCHAR2(10) NOT NULL
>- );

>STORE
>- CREATE TABLE STORE(
>-  MMSQ NUMBER(4) PRIMARY KEY ,
>-  STORENAME VARCHAR2(20) NOT NULL,
>-  ADDRESS VARCHAR2(100) NOT NULL,
>-  CATEGORY VARCHAR2(20) NOT NULL,
>-  NOTICE VARCHAR2(4000) NOT NULL,
>-  FOREIGN KEY (MMSQ) REFERENCES MEMBERMANAGER(MMSQ) ON DELETE CASCADE
>-  );

>STOREMENU
>- CREATE TABLE STOREMENU(
>- MMSQ NUMBER(4),
>- FOODNAME VARCHAR2(50) NOT NULL,
>- PRICE NUMBER(7) NOT NULL,
>- FOREIGN KEY (MMSQ) REFERENCES MEMBERMANAGER(MMSQ) ON DELETE CASCADE
>- );

>ORDERMANAGER
>- CREATE TABLE ORDERMANAGER(
>-  ODSQ NUMBER(4) PRIMARY KEY,
>-  MMSQ NUMBER(4) NOT NULL,
>- CONSUMERID VARCHAR2(20) NOT NULL,
>- STATUS VARCHAR2(20) NOT NULL,
>- ORDERDATE date not null,
>- FOREIGN KEY(MMSQ) REFERENCES MEMBERMANAGER(MMSQ) ON DELETE CASCADE,
>- FOREIGN KEY(CONSUMERID) REFERENCES MEMBERMANAGER(ID) ON DELETE CASCADE
>- );

>ORDERDETAIL
>- CREATE TABLE ORDERDETAIL(
>-  ODSQ NUMBER(4) NOT NULL,
>-  MMSQ NUMBER(4) NOT NULL,
>-  FOODNAME VARCHAR2(50) NOT NULL,
>-  FOREIGN KEY(ODSQ) REFERENCES ORDERMANAGER(ODSQ) ON DELETE CASCADE,
>-  FOREIGN KEY(MMSQ) REFERENCES MEMBERMANAGER(MMSQ) ON DELETE CASCADE
>-  );

>REVIEW
>- CREATE TABLE REVIEW (
>-  MMSQ NUMBER(4) NOT NULL,
>-  NICKNAME VARCHAR2(20) NOT NULL,
>-  REVIEWCOMMENT VARCHAR2(4000) NOT NULL,
>-  CREATEDDATE DATE NOT NULL,
>-  FOREIGN KEY(MMSQ) REFERENCES MEMBERMANAGER(MMSQ)
>-  );

>Sequence
>- CREATE SEQUENCE MMSQ START WITH 1 INCREMENT BY 1;
>- CREATE SEQUENCE ODSQ START WITH 1 INCREMENT BY 1;
---------------------------------------------------------

## 역할
- 최환영 Admin 클래스 개발
- 이승철 Owner 클래스 개발
- 박현민 Consumer 클래스 개발

## 공통
- OrderSystem 메인클래스 개발
- 데이터베이스 관리 클래스 개발
- 데이터베이스 테이블 설계
