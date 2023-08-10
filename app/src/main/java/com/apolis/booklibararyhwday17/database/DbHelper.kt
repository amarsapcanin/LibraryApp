package com.apolis.booklibararyhwday17.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.apolis.booklibararyhwday17.database.DatabaseConstants.CREATE_AUTHOR_TABLE
import com.apolis.booklibararyhwday17.database.DatabaseConstants.CREATE_BOOKS_TABLE
import com.apolis.booklibararyhwday17.database.DatabaseConstants.DATABASE_NAME
import com.apolis.booklibararyhwday17.database.DatabaseConstants.DATABASE_VERSION

class DbHelper(private val context: Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOKS_TABLE)
        db?.execSQL(CREATE_AUTHOR_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
