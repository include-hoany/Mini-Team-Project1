package BitOrderSystem;

public class LoginSession {
  static int mmsq = 0;
  static boolean isLogin = false;
  static String id = null;
  static String nickname = null;
  static String authority = null;

  // 로그아웃 메소드 LoginSession의 스타틱 변수 값을 null, false로 초기화 시킨다.
  public static void logout() {
    LoginSession.mmsq = 0;
    LoginSession.id = null;
    LoginSession.nickname = null;
    LoginSession.authority = null;
    LoginSession.isLogin = false;
  }

}