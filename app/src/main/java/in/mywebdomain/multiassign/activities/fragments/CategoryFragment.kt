package `in`.mywebdomain.multiassign.activities.fragments

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.activities.JokesListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.*

private const val ARG_PARAM1 = "param1"
class CategoryFragment : Fragment() {
    private var rootView : View? = null
    private var cat : String? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let{
            cat = it.getString(ARG_PARAM1).capitalize()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.item_category, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item_cat_tv.text = cat
        view.setOnClickListener { vie ->
            val intent = Intent(activity, JokesListActivity::class.java)
            intent.putExtra("catcat", cat?.toLowerCase())
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(param1 : String) = CategoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }
}