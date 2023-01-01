package democlass.websocket;

public class Auth {
    private String type = "Auth";
    private String token;

    public Auth(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\":\"" + type + "\"" +
                ",\"token\":\"" + token + "\"" +
                "}";
    }

    public static void main(String[] args) {
        Auth auth = new Auth("1","1");
        System.out.println(auth);
    }
}
