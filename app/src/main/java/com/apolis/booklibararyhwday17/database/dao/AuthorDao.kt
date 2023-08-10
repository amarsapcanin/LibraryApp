package com.apolis.booklibararyhwday17.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.apolis.booklibararyhwday17.database.DatabaseConstants.TABLE_AUTHOR
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.entity.Author

class AuthorDao(private val dbHelper: DbHelper) {

    fun addAuthor(author: Author): Long {
        val contentValue = ContentValues()

        contentValue.apply {
            put("name", author.name)
            put("rating", author.rating)
        }

        return dbHelper.writableDatabase.insert(TABLE_AUTHOR, null, contentValue)
    }

    fun getAllAuthors(): ArrayList<Author> {
        val authorList = ArrayList<Author>()

        val cursor = dbHelper.readableDatabase.query(TABLE_AUTHOR, null, null,
            null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("authorId"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val rating = cursor.getInt(cursor.getColumnIndexOrThrow("rating"))

            val author = Author(id = id, name = name, rating = rating)
            authorList.add(author)
        }
        cursor.close()

        return authorList
    }

    fun getAuthorById(authorId: Long): Author? {
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_AUTHOR, null,
            "authorId=?", arrayOf(authorId.toString()), null, null, null)

        return if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val rating = cursor.getInt(cursor.getColumnIndexOrThrow("rating"))
            Author(authorId, name, rating)
        } else {
            null
        }
    }

    fun updateAuthor(author: Author): Int {
        val contentValue = ContentValues()

        contentValue.apply {
            put("name", author.name)
            put("rating", author.rating)
        }

        return dbHelper.writableDatabase.update(
            TABLE_AUTHOR,
            contentValue,
            "name=?",
            arrayOf(author.name)
        )
    }


    fun deleteAuthor(authorId: Long): Int {
        return dbHelper.writableDatabase.delete(
            TABLE_AUTHOR,
            "authorId=?",
            arrayOf(authorId.toString())
        )
    }

    fun getThreeBestAuthors(): ArrayList<Author> {
        val authorList = ArrayList<Author>()

        val cursor = dbHelper.readableDatabase.query(
            TABLE_AUTHOR,
            null,
            null,
            null,
            null,
            null,
            "rating DESC", // Sort by rating in descending order to get the best authors first
            "3" // Limit the results to 3 authors
        )

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("authorId"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val rating = cursor.getInt(cursor.getColumnIndexOrThrow("rating"))

            val author = Author(id = id, name = name, rating = rating)
            authorList.add(author)
        }
        cursor.close()

        return authorList
    }
}

