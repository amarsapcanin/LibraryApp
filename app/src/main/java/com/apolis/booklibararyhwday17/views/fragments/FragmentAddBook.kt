package com.apolis.booklibararyhwday17.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.database.dao.BookDao
import com.apolis.booklibararyhwday17.database.entity.Author
import com.apolis.booklibararyhwday17.database.entity.Book
import com.apolis.booklibararyhwday17.databinding.FragmentAddBookBinding

class FragmentAddBook : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private lateinit var dbHelper: DbHelper
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDao()

        binding.btnSaveBook.setOnClickListener {
            addBookButton()
        }
    }

    private fun addBookButton() {
        val title = binding.etTitle.text.toString()
        val authorName = binding.etAuthor.text.toString()
        val category = binding.etCategory.text.toString()
        val year = binding.etYear.text.toString().toLongOrNull() ?: 0

        // Check if the author already exists in the database
        val existingAuthor = authorDao.getAllAuthors().find { it.name == authorName }

        val author: Author
        val authorId: Long

        if (existingAuthor != null) {
            // Author already exists, use the existing author
            author = existingAuthor
            authorId = existingAuthor.id
        } else {
            // Author does not exist, insert a new author into the database
            author = Author(0, authorName, 0) // Set a dummy rating for now, you can change it later
            authorId = authorDao.addAuthor(author)
        }

        // Create a book object with the author object
        val newBook = Book(0, title, author, category, year)

        val insertId = bookDao.addBook(newBook, authorId) // Pass the authorId to addBook()

        if (insertId > 0) {
            binding.etTitle.text?.clear()
            binding.etAuthor.text?.clear()
            binding.etCategory.text?.clear()
            binding.etYear.text?.clear()
        }
    }



    private fun initDao() {
        dbHelper = DbHelper(requireContext())
        authorDao = AuthorDao(dbHelper)
        bookDao = BookDao(dbHelper, authorDao)
    }
}
