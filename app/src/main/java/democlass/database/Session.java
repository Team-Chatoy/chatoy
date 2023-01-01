package democlass.database;

import java.util.Date;

public class Session {
    private int number; // The id of the user.
    private String agent; // The user agent of this session.
    private String token; // The token of this session. A hex string of length 64.
    private Date generated; // The time when this session was generated.
    private Date expired; // The time when this session expires.

    public Session(int number, String agent, String token, Date generated, Date expired) {
        this.number = number;
        this.agent = agent;
        this.token = token;
        this.generated = generated;
        this.expired = expired;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getGenerated() {
        return generated;
    }

    public void setGenerated(Date generated) {
        this.generated = generated;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Session{" +
                "number=" + number +
                ", agent='" + agent + '\'' +
                ", token='" + token + '\'' +
                ", generated=" + generated +
                ", expired=" + expired +
                '}';
    }
}
