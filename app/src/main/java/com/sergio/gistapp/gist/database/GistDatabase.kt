package com.sergio.gistapp.gist.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [GistEntity::class, FileEntity::class], version = 1, exportSchema = false)
abstract class GistDatabase : RoomDatabase() {

    abstract val gistDao: GistDao

    abstract val fileDao: FileDao

}
