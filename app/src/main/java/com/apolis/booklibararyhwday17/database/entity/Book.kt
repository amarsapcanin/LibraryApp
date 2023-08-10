package com.apolis.booklibararyhwday17.database.entity

data class Book(
    val id: Long,
    val title: String,
    val author: Author, // Change the type to Author instead of Long
    val category: String,
    val year: Long
)
