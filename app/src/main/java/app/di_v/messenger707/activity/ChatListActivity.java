package app.di_v.messenger707.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import app.di_v.messenger707.BottomNavigationDrawerFragment;
import app.di_v.messenger707.MessengerAdapter;
import app.di_v.messenger707.R;
import app.di_v.messenger707.database.MessengerViewModel;
import app.di_v.messenger707.database.User;


public class ChatListActivity extends AppCompatActivity {
    private static final String TAG = "main";
    public static final int AUTH_ACTIVITY_REQUEST_CODE = 0;
    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView mContactRecyclerView;
    private MessengerAdapter mAdapter;
    private MessengerViewModel mMessengerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Log.d(TAG, " start AuthorizationActivity ");
            startActivity(new Intent(this, AuthorizationActivity.class));
            this.finish();
        } else {
            mContactRecyclerView = findViewById(R.id.recycler_view);
            mContactRecyclerView.setHasFixedSize(true);
            mContactRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            mAdapter = new MessengerAdapter(this);
            mContactRecyclerView.setAdapter(mAdapter);

            mMessengerViewModel = new ViewModelProvider(this).get(MessengerViewModel.class);
            mMessengerViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable final List<User> users) {
                    // Update the cached copy of the words in the adapter.
                    Log.d(TAG, "add adapter data " + users.toString());
                    mAdapter.setUsers(users);
                }
            });

            setSupportActionBar((Toolbar) findViewById(R.id.bottom_app_bar));

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ChatListActivity.this, AddUserActivity.class);
                    startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User user = new User(data.getStringExtra(AddUserActivity.EXTRA_REPLY));
            mMessengerViewModel.insert(user);
        } else {
            Log.d(TAG, "main: Result code == error");
            Toast.makeText(
                    getApplicationContext(),
                    R.string.error,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                BottomNavigationDrawerFragment bt = new BottomNavigationDrawerFragment();
                bt.show(getSupportFragmentManager(), bt.getTag());
                return true;
            case R.id.app_bar_search:
                Toast toast = Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
