package com.devlab74.projectone.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.devlab74.projectone.R
import com.devlab74.projectone.databinding.LayoutBlogListItemBinding
import com.devlab74.projectone.model.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*
import java.text.DateFormat
import java.util.*

class MainRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BlogPost>() {

        override fun areItemsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutBlogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BlogPostViewHolder(
            binding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogPostViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<BlogPost>) {
        differ.submitList(list)
    }

    class BlogPostViewHolder
    constructor(
        private val binding: LayoutBlogListItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BlogPost) = with(binding) {
            binding.root.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            // need to shrink images b/c they are very high resolution
            val requestOptions = RequestOptions
                .overrideOf(1920, 1080)
            Glide.with(binding.root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(item.blog_image)
                .into(binding.blogImage)

            binding.blogTitle.text = item.title
            binding.blogBody.text = item.body
            binding.postedBy.text = item.posted_by

            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            val formattedPostedOn = dateFormat.format(item.date_posted!!)
            binding.postedOn.text = formattedPostedOn

            binding.repostsCount.text = item.reposts_count.toString()
            binding.likesCount.text = item.likes_count.toString()
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BlogPost)
    }
}