package com.example.bhagavatgeeta.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SaveChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapters(saveChapters: SaveChapters)

        @Query("SELECT * FROM SaveChapters")
        fun getSavedChapter(): LiveData<List<SaveChapters>>

        @Query("DELETE FROM SaveChapters WHERE id = :id")
        fun deleteChapter(id: Int)

        @Query("SELECT * FROM SaveChapters WHERE chapter_number = :chapter_number")
        fun getParticularChapter(chapter_number: Int): SaveChapters?





}
