package `in`.mywebdomain.multiassign.injection

import `in`.mywebdomain.multiassign.ui.category.CategoryListViewModel
import `in`.mywebdomain.multiassign.ui.jokes.JokesListViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.FragmentManager

class ViewModelFactory(private val catcat : String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return JokesListViewModel(catcat) as T
    }
}

class NViewModelFactory(private val fm : FragmentManager): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return CategoryListViewModel(fm) as T
    }
}

