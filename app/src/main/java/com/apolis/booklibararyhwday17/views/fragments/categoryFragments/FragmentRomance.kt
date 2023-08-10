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
import com.apolis.booklibararyhwday17.databinding.FragmentRomanceBinding
import com.apolis.booklibararyhwday17.views.adapters.AllBooksAdapter

class FragmentRomance : Fragment() {

    private lateinit var binding: FragmentRomanceBinding
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRomanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize database and DAOs
        val dbHelper = DbHelper(requireContext())
        authorDao = AuthorDao(dbHelper)
        bookDao = BookDao(dbHelper, authorDao)

        // Get Romance books from the database
        val romanceBooks = bookDao.getBooksByCategory("Romance")

        // Create and set up the RecyclerView adapter
        val adapter = AllBooksAdapter(romanceBooks)
        binding.rvRomanceBooks.adapter = adapter
        binding.rvRomanceBooks.layoutManager = LinearLayoutManager(requireContext())
    }
}
