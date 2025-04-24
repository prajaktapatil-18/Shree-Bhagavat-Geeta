package com.example.bhagavatgeeta.model

data class VerseItem(
    val chapter_number: Int,
    val commentaries: List<Commentary>,
    val id: Int,
    val text: String,
    val translations: List<Translation>,
    val verse_number: Int
)