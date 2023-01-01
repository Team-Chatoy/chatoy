package democlass.database;

public class MessageContent {
    /*
    We only support Text Message for now,
    but we will support more types of messages in the future :)
     */

    TextMessage text; // Conent.

    public MessageContent(TextMessage text) {
        this.text = text;
    }

    public TextMessage getText() {
        return text;
    }

    public void setText(TextMessage text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageContent{" +
                "text=" + text +
                '}';
    }
}
