package jni;

import java.util.List;

public class Utils {
  static {
    System.loadLibrary("chatoy");
  }

  private native Resp register(
    String server,
    String username,
    String password
  );

  private native Resp login(
    String server,
    String username,
    String password
  );

  private native User getUserInfo(
    String server,
    int id
  );

  private native int createRoom(
    String server,
    String token,
    String name
  );

  private native Resp joinRoom(
    String server,
    String token,
    int id
  );

  private native Room getRoomInfo(
    String server,
    int id
  );

  private native List<Room> getMyRooms(
    String server,
    String token
  );
}
