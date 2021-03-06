package com.davidnuon.canadaonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.davidnuon.canadaonline.R;
import com.davidnuon.canadaonline.model.ChatMessage;

import java.util.ArrayList;

/**
 * Created by davidnuon on 12/13/14.
 */
public class MessagesAdapter extends BaseAdapter {
    public final String TAG = "CANADA/ConverstionAdapter";
    Context mContext;
    ArrayList<ChatMessage> mMessages;
    ListView mListview;


    public Context getmContext() {
        return mContext;
    }

    public MessagesAdapter(Context mContext, ArrayList<ChatMessage> mMessages) {
        this.mContext = mContext;
        this.mMessages = mMessages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    public void adpot(ArrayList<ChatMessage> other) {
        mMessages.clear();
        mMessages.addAll(other);
        notifyDataSetChanged();
        if(mListview != null) {
            mListview.post(new Runnable() {
                @Override
                public void run() {
                    mListview.setSelection(mListview.getAdapter().getCount() - 1);
                }
            });
        }
    }

    public ListView getmListview() {
        return mListview;
    }

    public void setmListview(ListView mListview) {
        this.mListview = mListview;
    }

    @Override
    public Object getItem(int i) {
        return mMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChatMessage chatMessage = mMessages.get(i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_conversation, viewGroup, false);

        String language = String.format("( %s ): ", chatMessage.getLanguage());

        TextView sender = (TextView) rowView.findViewById(R.id.conversation_name);
        TextView lastMessage = (TextView) rowView.findViewById(R.id.last_message);

        if(i > 0) {
            if(mMessages.get(i - 1).getSender().equals( chatMessage.getSender() )) {
                sender.setVisibility(View.GONE);
            }
        }



        sender.setText(chatMessage.getSender());
        String lastMessageString = chatMessage.getMessage();
        lastMessage.setText(language + lastMessageString);

        return rowView;
    }
}
