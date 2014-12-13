package com.davidnuon.canadaonline.model;

import java.util.ArrayList;

/**
 * Created by davidnuon on 12/13/14.
 */
public class Conversation {
    ArrayList<ChatMessage> messages;
    String conversationName;
    boolean isRead;

    public Conversation(String conversationName) {
        this(new ArrayList<ChatMessage>(), conversationName, false);
    }

    private Conversation(ArrayList<ChatMessage> messages, String conversationName, boolean isRead) {
        this.messages = messages;
        this.conversationName = conversationName;
        this.isRead = isRead;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    /*
      ___           ___                       ___           ___
     /  /\         /  /\          ___        /  /\         /  /\          ___
    /  /:/_       /  /:/_        /  /\      /  /:/_       /  /:/_        /  /\
   /  /:/ /\     /  /:/ /\      /  /:/     /  /:/ /\     /  /:/ /\      /  /:/
  /  /:/_/::\   /  /:/ /:/_    /  /:/     /  /:/ /::\   /  /:/ /:/_    /  /:/
 /__/:/__\/\:\ /__/:/ /:/ /\  /  /::\    /__/:/ /:/\:\ /__/:/ /:/ /\  /  /::\
 \  \:\ /~~/:/ \  \:\/:/ /:/ /__/:/\:\   \  \:\/:/~/:/ \  \:\/:/ /:/ /__/:/\:\
  \  \:\  /:/   \  \::/ /:/  \__\/  \:\   \  \::/ /:/   \  \::/ /:/  \__\/  \:\
   \  \:\/:/     \  \:\/:/        \  \:\   \__\/ /:/     \  \:\/:/        \  \:\
    \  \::/       \  \::/          \__\/     /__/:/       \  \::/          \__\/
     \__\/         \__\/                     \__\/         \__\/

      */


    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
