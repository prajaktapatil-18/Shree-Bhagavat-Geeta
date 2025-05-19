package com.example.bhagavatgeeta.room

import android.content.Context
import android.content.IntentFilter
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SaveChapters::class], version = 1, exportSchema = false)
 abstract class AppDatabase :RoomDatabase() {



      abstract fun savedChapterFDao() :SaveChapterDao



    companion object{

        @Volatile
        var INSTANCE :  AppDatabase? = null


         fun getDatabaseInstance(context: Context) :AppDatabase?{

             val temInstance  :AppDatabase? = INSTANCE
             if (INSTANCE  != null) return  temInstance
             synchronized(this){

          val roomdb :AppDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"AppDatabase").fallbackToDestructiveMigration().build()




             }

         }

    }


}