package app.di_v.messenger707.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.di_v.messenger707.MessagesAdapter;
import app.di_v.messenger707.R;
import app.di_v.messenger707.database.MessengerViewModel;
import app.di_v.messenger707.database.Messages;
import app.di_v.messenger707.database.User;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private final static  String EXTRA_CONTACT_ID = "userId";
    private String mId;
    private EditText mMessageEditText;
    private Button mBtnPushMsg;
    private RecyclerView mRecyclerView;
    private MessagesAdapter mAdapter;
    private MessengerViewModel mMessengerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // set id
        mId = getIntent().getStringExtra(EXTRA_CONTACT_ID);
        //View
        mMessageEditText = findViewById(R.id.input_message);
        mBtnPushMsg = findViewById(R.id.btn_push_message);
        //RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view_messages);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MessagesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        List<User> users = new ArrayList<>();
        users.add(new User(mId, "user???"));
        users.add(new User("i"));
        mAdapter.setUsers(users);

        mMessengerViewModel = new ViewModelProvider(this).get(MessengerViewModel.class);
        mMessengerViewModel.getAllMessages().observe(this, new Observer<List<Messages>>() {
            @Override
            public void onChanged(@Nullable final List<Messages> messages) {
                // Update the cached copy of the words in the adapter.
                mAdapter.serMessages(messages);
            }
        });

        mBtnPushMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages msg = new Messages(mMessageEditText.getText().toString(), "i", 1);
                mMessengerViewModel.insertMsg(msg);
            }
        });

        Log.d(TAG, "User id: " + mId);
    }
}
