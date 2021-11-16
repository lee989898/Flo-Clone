
package com.example.flo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Song::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun SongDao(): SongDao

    companion object {
        private var instance: SongDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SongDatabase? {
            if (instance == null) {
                synchronized(SongDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "user-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }

    }

}