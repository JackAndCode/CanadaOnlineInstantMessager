package com.davidnuon.canadaonline.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.davidnuon.canadaonline.R;
import com.davidnuon.canadaonline.model.ChatMessage;
import com.davidnuon.canadaonline.model.Conversation;

import java.util.ArrayList;

/**
 * Created by davidnuon on 12/13/14.
 */
public class ConverstionAdapter extends BaseAdapter {
    public final String TAG = "CANADA/ConverstionAdapter";
    Context mContext;
    ArrayList<Conversation> mConverstions;

    public ConverstionAdapter(Context mContext, ArrayList<Conversation> mConverstions) {
        this.mContext = mContext;
        this.mConverstions = mConverstions;
    }

    @Override
    public int getCount() {
        return mConverstions.size();
    }

    @Override
    public Object getItem(int i) {
        return mConverstions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.i(TAG, "Inflating");

        Conversation currentConversation = mConverstions.get(i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_conversation, viewGroup, false);

        TextView converstaionName = (TextView) rowView.findViewById(R.id.conversation_name);
        TextView lastMessage = (TextView) rowView.findViewById(R.id.last_message);

        converstaionName.setText(currentConversation.getConversationName());
        String lastMessageString = "";
        ArrayList<ChatMessage> messages = currentConversation.getMessages();
        if( messages.size() < 1) {
            lastMessageString = "No messages yet.";
        } else  {
            lastMessageString = messages.get(messages.size() - 1).getMessage();
        }

        lastMessage.setText(lastMessageString);

        return rowView;
    }
}
