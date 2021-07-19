package BitOrderSystem;

// LoginSession 클래스는 로그인 정보를 담기위한 정적 변수를 가지고 있는 클래스
public class LoginSession {
  static int mmsq = 0;
  static boolean isLogin = false;
  static String id = null;
  static String nickname = null;
  static String authority = null;

  // 로그아웃 메소드 LoginSession의 정적 변수 값을 null, false로 초기화 시킨다.
  public static void logout() {
    LoginSession.mmsq = 0;
    LoginSession.id = null;
    LoginSession.nickname = null;
    LoginSession.authority = null;
    LoginSession.isLogin = false;
  }

}