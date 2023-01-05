package jni;

import java.util.List;

public class Utils {
  static {
    System.loadLibrary("chatoy_jni");
  }

  public native Resp register(
    String server,
    String username,
    String password
  );

  public native Resp login(
    String server,
    String username,
    String password
  );

  public native User getUserInfo(
    String server,
    int id
  );

  public native int createRoom(
    String server,
    String token,
    String name
  );

  public native Resp joinRoom(
    String server,
    String token,
    int id
  );

  public native Room getRoomInfo(
    String server,
    int id
  );

  public native List<Room> getMyRooms(
    String server,
    String token
  );
}
