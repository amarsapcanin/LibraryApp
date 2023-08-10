// FragmentAllBooks.kt
package com.apolis.booklibararyhwday17.views.fragments.categoryFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.database.dao.BookDao
import com.apolis.booklibararyhwday17.database.entity.Book
import com.apolis.booklibararyhwday17.databinding.FragmentAllBooksBinding
import com.apolis.booklibararyhwday17.views.adapters.AllBooksAdapter

class FragmentAllBooks : Fragment() {

    private lateinit var binding: FragmentAllBooksBinding
    private lateinit var bookDao: BookDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView
        val allBooksRecyclerView = binding.rvAllbooks

        // Initialize the BookDao and AuthorDao
        val dbHelper = DbHelper(requireContext())
        val authorDao = AuthorDao(dbHelper)
        bookDao = BookDao(dbHelper, authorDao)

        // Get the list of all books from the database
        val allBooksList = bookDao.getAllBooks()

        // Create and set up the adapter with the list of all books
        val allBooksAdapter = AllBooksAdapter(allBooksList)
        allBooksRecyclerView.adapter = allBooksAdapter

        // Set the layout manager for the RecyclerView
        allBooksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
