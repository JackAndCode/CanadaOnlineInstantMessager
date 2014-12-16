package com.davidnuon.canadaonline;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.davidnuon.canadaonline.adapter.MessagesAdapter;
import com.davidnuon.canadaonline.model.ChatMessage;
import com.davidnuon.canadaonline.model.Conversation;

import java.util.ArrayList;


public class ChatActivity extends ActionBarActivity {

    Conversation mConversation;
    ListView mListView;
    MessagesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mListView = (ListView) findViewById(R.id.listview_chat_list);
        mAdapter = new MessagesAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        String name = getIntent().getStringExtra("name");
        mConversation = new Conversation(name, mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
