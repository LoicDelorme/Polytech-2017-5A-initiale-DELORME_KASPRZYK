package fr.polytech.quizz.entities;

import java.io.Serializable;

public class IntentRequest implements Serializable {

    private final String messageKey;

    private final String message;

    public IntentRequest(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getMessage() {
        return message;
    }
}