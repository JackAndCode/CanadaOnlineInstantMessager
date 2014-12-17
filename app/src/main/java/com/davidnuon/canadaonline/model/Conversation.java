package com.davidnuon.canadaonline.model;


import android.os.AsyncTask;

import com.davidnuon.canadaonline.adapter.ConverstionAdapter;
import com.davidnuon.canadaonline.adapter.MessagesAdapter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by davidnuon on 12/13/14.
 */
public class Conversation {
    ArrayList<ChatMessage> messages;
    String conversationName;
    boolean isRead;
    MessagesAdapter mAdapter;

    public Conversation(String conversationName, MessagesAdapter mAdapter) {
        this(new ArrayList<ChatMessage>(), conversationName, false, mAdapter);
    }

    public Conversation(String conversationName) {
        this(new ArrayList<ChatMessage>(), conversationName, false, null);
    }

    private Conversation(ArrayList<ChatMessage> messages, String conversationName, boolean isRead,  MessagesAdapter mAdapter) {
        this.messages = messages;
        this.conversationName = conversationName;
        this.isRead = isRead;
        this.mAdapter = mAdapter;
        (new getMessage(conversationName, "android")).execute();
    }

    public void refresh() {
        // I should really just add an endpoint and poll for changes
        // So it'd be sending just a byte over the wire instead of
        // a giant blob...
        // Sorry!
        // (not sorry).
        (new getMessage(conversationName, "android")).execute();
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public void applyAdapter(ConverstionAdapter adpater, ArrayList<ChatMessage> messages) {

    }

    class getMessage extends AsyncTask<Void, Void, Void> {
        String mName;
        String mTo;

        getMessage(String mName, String mTo) {
            this.mName = mName;
            this.mTo = mTo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mAdapter != null) {
                mAdapter.adpot(messages);

            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://davidnuon.com/ernie-classic/?to=" + mName +"&name=android")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                messages.clear();
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject currentObject = (JSONObject) array.get(i);
                    ChatMessage singleMessage = new ChatMessage();
                    singleMessage.setLanguage(currentObject.optString("language"));
                    singleMessage.setMessage(currentObject.optString("message"));
                    singleMessage.setSender(currentObject.optString("name"));
                    singleMessage.setTimestamp(new Date(Integer.parseInt(currentObject.optString("date"))));
                    messages.add(singleMessage);
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmTo() {
            return mTo;
        }

        public void setmTo(String mTo) {
            this.mTo = mTo;
        }

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
