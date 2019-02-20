package `in`.mywebdomain.multiassign.base

import `in`.mywebdomain.multiassign.injection.component.DaggerViewModelInjector
import `in`.mywebdomain.multiassign.injection.component.ViewModelInjector
import `in`.mywebdomain.multiassign.injection.module.NetworkModule
import `in`.mywebdomain.multiassign.ui.category.CategoryListViewModel
import `in`.mywebdomain.multiassign.ui.jokes.JokesListViewModel
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is JokesListViewModel -> injector.inject(this)
        }
        when (this) {
            is CategoryListViewModel -> injector.inject(this)
        }
    }
}