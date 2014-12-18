package com.davidnuon.canadaonline;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.davidnuon.canadaonline.adapter.MessagesAdapter;
import com.davidnuon.canadaonline.model.ChatMessage;
import com.davidnuon.canadaonline.model.Conversation;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;


public class ChatActivity extends ActionBarActivity {

    Conversation mConversation;
    ListView mListView;
    MessagesAdapter mAdapter;
    EditText mMessageInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mListView = (ListView) findViewById(R.id.listview_chat_list);
        mAdapter = new MessagesAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        String name = getIntent().getStringExtra("name");

        setTitle("Online: " + name);
        mConversation = new Conversation(name, mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mConversation.refresh();
            }
        });
        mMessageInput = (EditText) findViewById(R.id.messageText);
        mMessageInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        String message = mMessageInput.getText().toString();
                        (new sendMessage(message)).execute();
                        mMessageInput.getText().clear();
                        return true;
                    }
                }

                return false;
            }
        });
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

    class sendMessage extends AsyncTask<Void, Void, Void> {

        String message;
        private boolean done;

        sendMessage(String message) {
            this.message = message;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(done) {
                mConversation.refresh();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormEncodingBuilder()
                    .add("method" ,  "write")
                    .add("name" ,  "android")
                    .add("message" , this.message)
                    .add("to", mConversation.getConversationName())
                    .add("language" ,  "EN")
                    .build();

            Request request = new Request.Builder()
                    .url("http://davidnuon.com/ernie-classic/?method=write")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()) {
                    this.done = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
