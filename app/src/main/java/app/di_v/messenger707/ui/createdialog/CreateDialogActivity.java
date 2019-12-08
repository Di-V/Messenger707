package app.di_v.messenger707.ui.createdialog;

import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;
import app.di_v.messenger707.ui.BaseActivity;

public class CreateDialogActivity extends BaseActivity {

    @NotNull
    @Override
    public Fragment createFragment() {
        return CreateDialogFragment.newInstance();
    }
}
