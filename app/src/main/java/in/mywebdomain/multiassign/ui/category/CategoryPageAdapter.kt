package `in`.mywebdomain.multiassign.ui.category

import `in`.mywebdomain.multiassign.activities.fragments.CategoryFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class CategoryPageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val fragmentList : MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun updateCategories(lst : List<String>) {
        for(str : String in lst)
            fragmentList.add(CategoryFragment.newInstance(str))
        notifyDataSetChanged()
    }

}