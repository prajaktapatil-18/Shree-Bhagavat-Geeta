package com.example.bhagavatgeeta.model

import androidx.room.PrimaryKey

data class chaptersItem(


    val chapter_number: Int,
    val chapter_summary: String,

    @PrimaryKey
    val id: Int,
    val name: String,
    val name_meaning: String,
    val name_translated: String,
    val name_transliterated: String,
    val verses_count: Int
)