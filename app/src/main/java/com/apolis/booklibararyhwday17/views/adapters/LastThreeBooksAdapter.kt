package com.apolis.booklibararyhwday17.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.booklibararyhwday17.database.entity.Book
import com.apolis.booklibararyhwday17.databinding.AllbooklistItemBinding

class NewestBooksAdapter(private val booksList: List<Book>) :
    RecyclerView.Adapter<NewestBooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AllbooklistItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(booksList[position])
    }

    inner class BookViewHolder(private val binding: AllbooklistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {
            tvBook.text = book.title
            tvAuthor.text = book.author.name // Use the author's name instead of the authorId
            tvCategory.text = book.category
            tvYear.text = book.year.toString() // Display the book's year
        }
    }
}
