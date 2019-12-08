package app.di_v.messenger707.ui.dialoglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.di_v.messenger707.R
import app.di_v.messenger707.adapters.MessengerAdapter
import app.di_v.messenger707.data.model.ListUsers

class DialogListFragment private constructor(): Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = DialogListFragment()
    }

    lateinit var mMsgRecyclerView : RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dialog_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMsgRecyclerView = view.findViewById(R.id.recycler_view)
        mMsgRecyclerView.setHasFixedSize(true)
        mMsgRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MessengerAdapter()
        mMsgRecyclerView.adapter = adapter
        val mMessengerViewModel = ViewModelProvider(this).get(MessengerViewModel::class.java)
        mMessengerViewModel.getListAllUsers().observe(viewLifecycleOwner, Observer<List<ListUsers>> {
            users -> adapter.setUsers(users)
        })
    }
}