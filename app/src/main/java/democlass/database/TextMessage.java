package democlass.database;

public class TextMessage {
    private String type; // The type of this message.
    private String textMessage; // The text of this message.

    public TextMessage(String type, String textMessage) {
        this.type = type;
        this.textMessage = textMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "type='" + type + '\'' +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
