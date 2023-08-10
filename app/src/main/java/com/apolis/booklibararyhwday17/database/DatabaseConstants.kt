package com.apolis.booklibararyhwday17.database

object DatabaseConstants {

    const val DATABASE_NAME = "library"
    const val DATABASE_VERSION = 1
    const val TABLE_BOOKS = "books"
    const val TABLE_AUTHOR = "author"


    val CREATE_BOOKS_TABLE = """ CREATE TABLE $TABLE_BOOKS(
        bookId INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT,
        author_id INTEGER,
        category TEXT,
        year INTEGER)
    """.trimIndent()

    val CREATE_AUTHOR_TABLE = """ CREATE TABLE $TABLE_AUTHOR(
        authorId INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        rating INTEGER)
    """.trimIndent()

}
