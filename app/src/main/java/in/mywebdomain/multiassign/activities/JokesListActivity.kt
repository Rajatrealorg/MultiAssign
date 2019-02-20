package `in`.mywebdomain.multiassign.activities

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.databinding.ActivityJokesListBinding
import `in`.mywebdomain.multiassign.injection.ViewModelFactory
import `in`.mywebdomain.multiassign.ui.jokes.JokesListViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager

class JokesListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityJokesListBinding
    private lateinit var viewModel: JokesListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!intent.hasExtra("catcat"))
            intent.putExtra("catcat", "nerdy")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_jokes_list)
        binding.jokesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this , ViewModelFactory(intent.getStringExtra("catcat"))).get(JokesListViewModel::class.java)
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
