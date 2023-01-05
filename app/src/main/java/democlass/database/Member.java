package democlass.database;

import java.util.Date;

public class Member {
    private int user; // Who joins the room
    private int room; // Which room the user joins.
    private Date joined; // When the user joins the room.

    public Member(int user, int room, Date joined) {
        this.user = user;
        this.room = room;
        this.joined = joined;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Member{" +
                "user=" + user +
                ", room=" + room +
                ", joined=" + joined +
                '}';
    }
}
