package ro.stancalau.springfx.model;

import java.util.Observable;

public class MessageModel extends Observable {

    private String message;

    public MessageModel() {
        setMessage("Default Message!");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null || message.equals(this.message)) return;
        this.message = message;
        setChanged();
        notifyObservers();
    }
}
