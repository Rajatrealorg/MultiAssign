package `in`.mywebdomain.multiassign.ui.jokes

import `in`.mywebdomain.multiassign.R
import `in`.mywebdomain.multiassign.databinding.ItemJokeBinding
import `in`.mywebdomain.multiassign.model.Jokes
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class JokesListAdapter: RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {
    private lateinit var jokeList:List<Jokes>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesListAdapter.ViewHolder {
        val binding: ItemJokeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_joke, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokesListAdapter.ViewHolder, position: Int) {
        holder.bind(jokeList[position])
    }

    override fun getItemCount(): Int {
        return if(::jokeList.isInitialized) jokeList.size else 0
    }

    fun updatJokesList(jokesList : List<Jokes>){
        this.jokeList = jokesList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemJokeBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = JokesViewModel()

        fun bind(jokes: Jokes){
            viewModel.bind(jokes)
            binding.viewModel = viewModel
        }
    }
}