package com.davidnuon.canadaonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.davidnuon.canadaonline.adapter.ConverstionAdapter;
import com.davidnuon.canadaonline.model.Conversation;

import java.util.ArrayList;


public class BuddyListActivity extends ActionBarActivity {
    public final String TAG = "CANADA/BuddyListActivity";
    ListView mConversationList;
    private ConverstionAdapter mConversationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_list);

        Log.i(TAG, "Got here");
        ArrayList<Conversation> conversations = new ArrayList<>();

        conversations.add(new Conversation("Kelo"));
        conversations.add(new Conversation("Henri"));
        conversations.add(new Conversation("David"));

        mConversationListAdapter = new ConverstionAdapter(this, conversations);
        mConversationList = (ListView) findViewById(R.id.listview_buddy_list);
        mConversationList.setAdapter(mConversationListAdapter);

        mConversationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuddyListActivity.this, ChatActivity.class);
                intent.putExtra("name", ((Conversation) (adapterView.getAdapter().getItem(i))).getConversationName()  );
                BuddyListActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buddy_list, menu);
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