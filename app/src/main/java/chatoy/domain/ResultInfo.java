package domain;

public class ResultInfo {
    private boolean flag;
    private Object data;
    private String message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo{" + "\n" +
                "flag= " + flag + "\n" +
                "data= " + data + "\n" +
                "message= " + message + "\n" +
                "}" + "\n";
    }
}
