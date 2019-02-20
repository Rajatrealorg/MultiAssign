package `in`.mywebdomain.multiassign.ui.jokes

import `in`.mywebdomain.multiassign.base.BaseViewModel
import `in`.mywebdomain.multiassign.model.Jokes
import android.arch.lifecycle.MutableLiveData

class JokesViewModel : BaseViewModel() {
    private val aJoke = MutableLiveData<String>()
    private val aJID = MutableLiveData<Int>()

    fun bind(joke: Jokes){
        aJoke.value = joke.joke
        aJID.value = joke.id
    }

    fun getJoke() : MutableLiveData<String> {
        return aJoke
    }
}