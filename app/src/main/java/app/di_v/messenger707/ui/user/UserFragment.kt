package app.di_v.messenger707.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.di_v.messenger707.R

class UserFragment private constructor(): Fragment() {

    companion object {
        private val EXTRA_INFO_USER_ID = "app.di_v.messenger707.info_user_id"

        fun newInstance(id: String): Fragment {
            val args = Bundle()
            args.putSerializable(EXTRA_INFO_USER_ID, id)
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id =""
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val user = viewModel.getUser(id)
    }
}