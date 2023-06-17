package dev.a8kj7sea.pa.object;

public class AnnounceObject {

    public static enum MessageType {
        TEXT, EMBED;
    }

    private String message;
    private MessageType messageType;

    public AnnounceObject(String message, MessageType messageType) {
        setMessage(message);
        setMessageType(messageType);
    }

    public AnnounceObject(String message) {
        setMessage(message);
        setMessageType(MessageType.TEXT);
    }

    public AnnounceObject() {
        setMessage("");
        setMessageType(MessageType.TEXT);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

}
