package `in`.mywebdomain.multiassign.ui.jokes

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.base.BaseViewModel
import `in`.mywebdomain.multiassign.model.Jokes
import `in`.mywebdomain.multiassign.model.JokesAuto
import `in`.mywebdomain.multiassign.network.JokesApi
import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class JokesListViewModel(private val catcat : String) : BaseViewModel() {
    @Inject
    lateinit var jokesApi: JokesApi
    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadJokes() }
    val jokesListAdapter : JokesListAdapter = JokesListAdapter()

    init{
        loadJokes()
    }

    private fun loadJokes(){
        subscription = jokesApi.getJokes(catcat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCategoryListStart() }
                .doOnTerminate { onRetrieveCategoryListFinish() }
                .subscribe(
                        { result -> onRetrieveCategoryListSuccess(result) },
                        { onRetrieveCategoryListError() }
                )
    }

    private fun onRetrieveCategoryListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCategoryListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListSuccess(jokesAuto: JokesAuto){
        val jokesList : List<Jokes> = jokesAuto.value
        jokesListAdapter.updatJokesList(jokesList)
    }

    private fun onRetrieveCategoryListError() {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}