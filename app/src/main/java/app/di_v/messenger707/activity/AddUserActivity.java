package app.di_v.messenger707.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import app.di_v.messenger707.R;

public class AddUserActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "app.di_v.messengerlistsql.REPLY";

    private EditText mEditContactView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_add_user);
        mEditContactView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.btn_save_contact);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContactView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String contact = mEditContactView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, contact);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
