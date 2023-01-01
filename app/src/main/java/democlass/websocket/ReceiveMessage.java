package democlass.websocket;

import democlass.database.Message;
import democlass.database.MessageContent;
import democlass.database.TextMessage;

import java.util.Date;

public class ReceiveMessage {
    private String type = "Recv";
    private Message data;

    public ReceiveMessage(String type, Message data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\n  \"type\": \"" + type + "\"" +
                ",\n  \"data\": {" +
                ",\n    \"uuid\": \"" + data.getUuid()+ "\"" +
                ",\n    \"sender\": " + data.getSender() +
                ",\n    \"data\": {" +
                "\n      \"type: \"" + data.getData().getText().getType()+ "\"" +
                ",\n      \"text: \"" + data.getData().getText().getTextMessage() + "\"" +
                "\n    }" +
                ",\n    \"sent\": \"" + data.getSender() + "\"" +
                "\n    \"modified\": " + data.isModified() +
                "\n  }" +
                "\n}";
    }

    public static void main(String[] args) {
        ReceiveMessage receiveMessage = new ReceiveMessage("1"
                , new Message("1", 1, 1, new MessageContent(
                        new TextMessage("1", "1"))
                , new Date(), true
        ));

        System.out.println(receiveMessage);
    }
}
