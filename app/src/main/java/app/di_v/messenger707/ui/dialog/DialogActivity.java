package app.di_v.messenger707.ui.dialog;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import app.di_v.messenger707.ui.BaseActivity;

public class DialogActivity extends BaseActivity {
    private static final String EXTRA_USER_ID = "app.di_v.messenger707.user_id";

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, DialogActivity.class);
        intent.putExtra(EXTRA_USER_ID, id);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        String id = String.valueOf(getIntent()
                .getSerializableExtra(EXTRA_USER_ID));
        return DialogFragment.newInstance(id);
    }
}
