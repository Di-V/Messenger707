package app.di_v.messenger707;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ContactListActivity extends AppCompatActivity {
    private RecyclerView mContactRecyclerView;
    private MessengerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mContactRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mContactRecyclerView.setLayoutManager(layoutManager);

        //DataSet
        int len = Contacts.cont.length;
        String[] c = new String[len];
        int[] img = new int[len];
        for (int i = 0; i < len; i++) {
            c[i] = Contacts.cont[i].getContact();
            img[i] = Contacts.cont[i].getImgContact();
        }

        // specify an adapter (see also next example)
        mAdapter = new MessengerAdapter(c, img);
        mContactRecyclerView.setAdapter(mAdapter);

        setSupportActionBar((Toolbar) findViewById(R.id.bottom_app_bar));
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
