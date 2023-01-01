package democlass.websocket;

import democlass.database.MessageContent;
import democlass.database.TextMessage;

public class SendMessage {
    private String type = "Msg";
    private String uuid;
    private int room;
    private MessageContent data;

    public SendMessage(String type, String uuid, int room, MessageContent data) {
        this.type = type;
        this.uuid = uuid;
        this.room = room;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public MessageContent getData() {
        return data;
    }

    public void setData(MessageContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\n  \"type\": \"" + type + "\"" +
                ",\n  \"uuid\": \"" + uuid + "\"" +
                ",\n  \"room\": " + room +
                ",\n  \"data\": {" +
                ",\n    \"type: \"" + data.getText().getType() + "\"" +
                ",\n    \"text: \"" + data.getText().getTextMessage() + "\"" +
                "\n  }" +
                "\n}";
    }

    public static void main(String[] args) {
        System.out.println(new SendMessage("1", "1", 1, new MessageContent(new TextMessage("1","1"))));
    }
}
