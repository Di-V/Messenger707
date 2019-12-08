package app.di_v.messenger707.ui.dialoglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import app.di_v.messenger707.R;
import app.di_v.messenger707.ui.createdialog.CreateDialogActivity;
import app.di_v.messenger707.ui.auth.AuthorizationActivity;

public class DialogListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_list);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.recycler_view);
            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .add(R.id.container, DialogListFragment.newInstance())
                        .commit();
            }

            setSupportActionBar((Toolbar) findViewById(R.id.bottom_app_bar));

            FloatingActionButton fab = findViewById(R.id.fab_dialog_list);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DialogListActivity.this, CreateDialogActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            startActivity(new Intent(this, AuthorizationActivity.class));
            this.finish();
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
