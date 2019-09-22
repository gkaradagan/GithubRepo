package com.gorkem.githubrepo.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorkem.githubrepo.base.LiveEvent
import com.gorkem.githubrepo.base.ShimmerModel
import com.gorkem.githubrepo.base.ShimmerViewHolder
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.databinding.RepoListItemRowBinding
import com.gorkem.githubrepo.databinding.RepoListItemRowShimmerBinding

class GithupRepoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listen = LiveEvent<Int>()
    private val itemList = mutableListOf<Any>()

    companion object {
        private const val TYPE_LOADING = 0
        private const val TYPE_SHOW = 1
    }

    override fun getItemViewType(position: Int): Int {
        val get = itemList[position]
        return when (get) {
            is GithubRepoResponse -> TYPE_SHOW
            is ShimmerModel -> TYPE_LOADING
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    fun showLoading() {
        clearItems()
        itemList.addAll(
            listOf(
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel()
            )
        )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SHOW -> RepoListViewHolder(
                RepoListItemRowBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ), listen
            )
            TYPE_LOADING -> ShimmerViewHolder(
                RepoListItemRowShimmerBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    fun listen(): LiveEvent<Int> = listen

    fun addItems(list: List<GithubRepoResponse>) {
        clearItems()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun updateRepoStar(id: Int, favourite: Boolean) {
        val indexOfFirst = itemList.indexOfFirst {
            if (it is GithubRepoResponse)
                it.id == id
            else false
        }
        if (indexOfFirst != -1) {
            val get = itemList.get(indexOfFirst)
            if (get is GithubRepoResponse) {
                (itemList.get(indexOfFirst) as GithubRepoResponse).favourite = favourite
                notifyItemChanged(indexOfFirst)
            }
        }
    }

    fun clearItems() {
        itemList.clear()
    }

    fun getItems() = itemList

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = itemList.get(position)
        when (data) {
            is GithubRepoResponse -> (holder as RepoListViewHolder).bind(data)
        }
    }

    class RepoListViewHolder(
        var binding: RepoListItemRowBinding,
        private val listen: LiveEvent<Int>
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listen.value = adapterPosition
            }
        }

        fun bind(data: GithubRepoResponse) {
            binding.item = data
            binding.executePendingBindings()
        }
    }
}