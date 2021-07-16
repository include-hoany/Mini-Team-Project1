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
- 소비자 회원가입
- 종료

### 관리자
- 가게등록
- 가게 정보 수정 삭제

### 가게
- 메뉴등록
- 메뉴가격수정
- 주문확인
- 공지

### 소비자
- 주문
- 주문상태확인
- 리뷰등록
- 리뷰확인
---------------------------------------------------------

## Class 구조
### 메인이 있는 클래스
- class OrderSystem

### 관리자 클래스
- class Admin

### 고객 클래스
- class Consumer

### 판매자 클래스
- class Owner

### 디비 관리 클래스
- class OrderDb

### 로그인 정보 확인 클래스
- class LoginSession

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
- 최환영 관리자 클래스 개발
- 이승철 판매자 클래스 개발
- 박현민 소비자 클래스 개발

## 공통
- OrderSystem 메인클래스 개발
- 데이터베이스 관리 클래스 개발
- 데이터베이스 테이블 설계
