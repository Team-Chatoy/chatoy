package democlass.database;

import java.util.Date;

public class User {
    private int id; // Each user has a unique id.
    private String username; // User uses this name to sign in.
    private String nickname; // This name will be displayed on the user information.
    private String password; // Password hashed with BLAKE31 algorithm. A hex string of length 64.
    private String slogan; // Introduction or motto, or something else.
    private int status; // 0: The account is normal.1: The user actively destroyed the account.
    private Date timestamp; // The time when this account was registered.

    public User(int id, String username, String nickname, String password, String slogan, int status, Date timestamp) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.slogan = slogan;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", slogan='" + slogan + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
