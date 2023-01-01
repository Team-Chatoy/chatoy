package democlass.websocket;

public class Response {
    private String type = "Auth"; // This message is the response of the auth message.
    private int code; // The status code of the response. It will be 0 if the auth succeeds.
    private String msg; // The message of the response. It will be empty if the auth succeeds.

    public Response(String type, int code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\":\"" + type + "\"" +
                ",\"code\":" + code +
                ",\"msg\":\"" + msg + "\"" +
                '}';
    }

    public static void main(String[] args) {
        Response response = new Response("1", 1, "1");
        System.out.println(response);
    }
}
