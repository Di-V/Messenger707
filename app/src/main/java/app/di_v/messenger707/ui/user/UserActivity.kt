package app.di_v.messenger707.ui.user

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import app.di_v.messenger707.ui.BaseActivity
import app.di_v.messenger707.ui.user.UserFragment.Companion.newInstance

class UserActivity: BaseActivity() {
    override fun createFragment(): Fragment {
        val id = intent.getSerializableExtra(EXTRA_INFO_USER_ID).toString()
        return newInstance(id)
    }

    companion object {
        private val EXTRA_INFO_USER_ID = "app.di_v.messenger707.info_user_id"

        @JvmStatic
        fun newIntent(packageContext: Context, id: String): Intent? {
            val intent = Intent(packageContext, UserActivity::class.java)
            intent.putExtra(EXTRA_INFO_USER_ID, id)
            return intent
        }
    }

}