package com.apolis.booklibararyhwday17.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.database.dao.BookDao
import com.apolis.booklibararyhwday17.databinding.FragmentHomeBinding
import com.apolis.booklibararyhwday17.views.adapters.AllBooksAdapter
import com.apolis.booklibararyhwday17.views.adapters.BestAuthorsAdapter
import com.apolis.booklibararyhwday17.views.adapters.NewestBooksAdapter

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbHelper: DbHelper
    private lateinit var bookDao: BookDao
    private lateinit var authorDao: AuthorDao



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDao()

        val allBestAuthors = authorDao.getThreeBestAuthors()

        val adapterAuthors = BestAuthorsAdapter(allBestAuthors)
        binding.rvTopAuthors.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvTopAuthors.adapter = adapterAuthors

        val allNewBooks = bookDao.getThreeNewestBooks()

        val adapterBooks = NewestBooksAdapter(allNewBooks)
        binding.rvAllbooks.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAllbooks.adapter = adapterBooks




    }

    private fun initDao() {
        dbHelper = DbHelper(requireContext())
        authorDao = AuthorDao(dbHelper)
        bookDao = BookDao(dbHelper, authorDao) // Pass the AuthorDao instance here
    }
}