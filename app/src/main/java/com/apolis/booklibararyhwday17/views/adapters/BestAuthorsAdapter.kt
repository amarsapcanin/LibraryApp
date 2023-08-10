package com.apolis.booklibararyhwday17.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.booklibararyhwday17.database.entity.Author
import com.apolis.booklibararyhwday17.databinding.AuthorbestItemBinding

class BestAuthorsAdapter(private val authorsList: List<Author>) :
    RecyclerView.Adapter<BestAuthorsAdapter.AuthorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AuthorbestItemBinding.inflate(layoutInflater, parent, false)
        return AuthorViewHolder(binding)
    }

    override fun getItemCount(): Int = authorsList.size

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.bind(authorsList[position])
    }

    inner class AuthorViewHolder(private val binding: AuthorbestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(author: Author) = with(binding) {
            tvName.text = author.name
        }
    }
}
