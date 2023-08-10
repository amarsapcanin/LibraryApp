package com.apolis.booklibararyhwday17.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.apolis.booklibararyhwday17.database.DatabaseConstants.TABLE_BOOKS
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.entity.Author
import com.apolis.booklibararyhwday17.database.entity.Book

class BookDao(private val dbHelper: DbHelper, private val authorDao: AuthorDao) {


    fun addBook(book: Book, authorId: Long): Long {
        val contentValue = ContentValues()

        contentValue.apply {
            put("title", book.title)
            put("author_id", authorId)
            put("year", book.year)
            put("category", book.category)
        }

        return dbHelper.writableDatabase.insert(TABLE_BOOKS, null, contentValue)
    }

    fun getAllBooks(): ArrayList<Book> {
        val bookList = ArrayList<Book>()

        val cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, null,
            null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("bookId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val authorId = cursor.getLong(cursor.getColumnIndexOrThrow("author_id"))
            val year = cursor.getLong(cursor.getColumnIndexOrThrow("year"))
            val category = cursor.getString(cursor.getColumnIndexOrThrow("category"))

            val author = authorDao.getAuthorById(authorId)

            val book = Book(
                id = id,
                title = title,
                author = author!!,
                year = year,
                category = category
            )
            bookList.add(book)
        }
        cursor.close()

        return bookList
    }

    fun getThreeNewestBooks(): ArrayList<Book> {
        val bookList = ArrayList<Book>()

        val cursor = dbHelper.readableDatabase.query(
            TABLE_BOOKS,
            null,
            null,
            null,
            null,
            null,
            "year DESC",
            "2"
        )

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("bookId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val authorId = cursor.getLong(cursor.getColumnIndexOrThrow("author_id"))
            val year = cursor.getLong(cursor.getColumnIndexOrThrow("year"))
            val category = cursor.getString(cursor.getColumnIndexOrThrow("category"))

            val author = authorDao.getAuthorById(authorId)

            val book = Book(
                id = id,
                title = title,
                author = author ?: Author(0, "", 0),
                year = year,
                category = category
            )
            bookList.add(book)
        }
        cursor.close()

        return bookList
    }

    fun getBooksByCategory(category: String): ArrayList<Book> {
        val bookList = ArrayList<Book>()

        val cursor = dbHelper.readableDatabase.query(
            TABLE_BOOKS,
            null,
            "category = ?",
            arrayOf(category),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("bookId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val authorId = cursor.getLong(cursor.getColumnIndexOrThrow("author_id"))
            val year = cursor.getLong(cursor.getColumnIndexOrThrow("year"))

            val author = authorDao.getAuthorById(authorId)

            val book = Book(
                id = id,
                title = title,
                author = author ?: Author(0, "", 0),
                year = year,
                category = category
            )
            bookList.add(book)
        }
        cursor.close()

        return bookList
    }




}
