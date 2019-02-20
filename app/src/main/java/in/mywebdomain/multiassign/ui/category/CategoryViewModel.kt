package `in`.mywebdomain.multiassign.ui.category

import `in`.mywebdomain.multiassign.base.BaseViewModel
import android.arch.lifecycle.MutableLiveData

class CategoryViewModel : BaseViewModel() {
    private val aCategory = MutableLiveData<String>()

    fun bind(cat: String) {
        aCategory.value = cat
    }

    fun getCategory() : MutableLiveData<String> {
        return aCategory
    }

}