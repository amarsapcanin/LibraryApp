package com.apolis.booklibararyhwday17.views.fragments.categoryFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.database.dao.BookDao
import com.apolis.booklibararyhwday17.databinding.FragmentActionBinding
import com.apolis.booklibararyhwday17.views.adapters.AllBooksAdapter

class FragmentAction : Fragment() {

    private lateinit var binding: FragmentActionBinding
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize database and DAOs
        val dbHelper = DbHelper(requireContext())
        authorDao = AuthorDao(dbHelper)
        bookDao = BookDao(dbHelper, authorDao)

        // Get Action books from the database
        val actionBooks = bookDao.getBooksByCategory("Action")

        // Create and set up the RecyclerView adapter
        val adapter = AllBooksAdapter(actionBooks)
        binding.rvActionBooks.adapter = adapter
        binding.rvActionBooks.layoutManager = LinearLayoutManager(requireContext())
    }
}
