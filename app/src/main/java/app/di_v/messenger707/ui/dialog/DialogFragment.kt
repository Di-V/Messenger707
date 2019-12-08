package app.di_v.messenger707.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.di_v.messenger707.R
import app.di_v.messenger707.adapters.MessagesAdapter
import app.di_v.messenger707.data.model.Messages
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DialogFragment private constructor(): Fragment() {

    companion object {
        private val EXTRA_USER_ID = "app.di_v.messenger707.user_id"

        @JvmStatic
        fun newInstance(id: String): Fragment {
            val args = Bundle()
            args.putSerializable(EXTRA_USER_ID, id)
            val fragment = DialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mMessageEditText: EditText
    private lateinit var mBtnPushMsg: FloatingActionButton
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MessagesAdapter
    private lateinit var mMessengerViewModel: DialogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMessageEditText = view.findViewById(R.id.input_message)
        mBtnPushMsg = view.findViewById(R.id.fab_send)
        mRecyclerView = view.findViewById(R.id.recycler_view_messages)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mId = arguments!!.getSerializable(EXTRA_USER_ID) as String

        mAdapter = MessagesAdapter()
        mRecyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.stackFromEnd = true
        mRecyclerView.layoutManager = layoutManager

        mMessengerViewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        mMessengerViewModel.getAllMessagesFromUser(mId)!!.observe(viewLifecycleOwner, Observer {
            messages -> mAdapter.setUsers(messages)
        })

        mBtnPushMsg.setOnClickListener {
            val msg = Messages(mMessageEditText.text.toString(), mId, 0)
            mMessengerViewModel.insertMsg(msg)
            mMessageEditText.setText("")
        }
    }
}