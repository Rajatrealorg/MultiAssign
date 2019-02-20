package `in`.mywebdomain.multiassign.ui.category

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.base.BaseViewModel
import `in`.mywebdomain.multiassign.model.JCategoryAuto
import `in`.mywebdomain.multiassign.network.JokesApi
import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.FragmentManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryListViewModel(fm : FragmentManager) : BaseViewModel() {
    @Inject
    lateinit var jokesApi: JokesApi
    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCategories() }
    var categoryPageAdapter: CategoryPageAdapter = CategoryPageAdapter(fm)

    init {
        loadCategories()
    }

    private fun loadCategories() {
        subscription = jokesApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveJokesListStart() }
                .doOnTerminate { onRetrieveJokesListFinish() }
                .subscribe(
                        { result -> onRetrieveJokesListSuccess(result) },
                        { onRetrieveJokesListError() }
                )
    }

    private fun onRetrieveJokesListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveJokesListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveJokesListSuccess(categoriesAuto: JCategoryAuto){
        val categoryList : List<String> = categoriesAuto.value
        categoryPageAdapter.updateCategories(categoryList)
    }

    private fun onRetrieveJokesListError() {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}