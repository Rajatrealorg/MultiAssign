package `in`.mywebdomain.multiassign.activities

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.databinding.ActivityCategoryBinding
import `in`.mywebdomain.multiassign.injection.NViewModelFactory
import `in`.mywebdomain.multiassign.ui.category.CategoryListViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding
    private lateinit var viewModel: CategoryListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)
        viewModel = ViewModelProviders.of(this , NViewModelFactory(supportFragmentManager)).get(CategoryListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
