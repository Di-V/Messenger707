package app.di_v.messenger707.ui.createdialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import app.di_v.messenger707.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateDialogFragment private constructor(): Fragment(){

    companion object {
        @JvmStatic
        fun newInstance() = CreateDialogFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_back)
        fab.setOnClickListener {
            activity?.finish()
        }
    }
}