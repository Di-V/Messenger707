package app.di_v.messenger707.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.di_v.messenger707.MessagesAdapter;
import app.di_v.messenger707.R;
import app.di_v.messenger707.data.MessengerViewModel;
import app.di_v.messenger707.data.model.Messages;
import app.di_v.messenger707.data.model.UserMessages;

public class ChatActivity extends AppCompatActivity {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MessagesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mMessengerViewModel = new ViewModelProvider(this).get(MessengerViewModel.class);
        mMessengerViewModel.getAllMessagesFromUser(mId).observe(this, new Observer<UserMessages>() {
            @Override
            public void onChanged(@Nullable final UserMessages messages) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setUsers(messages);
            }
        });

        mBtnPushMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages msg = new Messages(mMessageEditText.getText().toString(), mId, 0);
                mMessengerViewModel.insertMsg(msg);
                mMessageEditText.setText("");
            }
        });
    }
}
