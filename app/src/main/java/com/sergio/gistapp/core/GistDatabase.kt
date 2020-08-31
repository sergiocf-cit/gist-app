package com.sergio.gistapp.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sergio.gistapp.gist.database.FileDao
import com.sergio.gistapp.gist.database.FileEntity
import com.sergio.gistapp.gist.database.GistDao
import com.sergio.gistapp.gist.database.GistEntity


@Database(entities = [GistEntity::class, FileEntity::class], version = 1, exportSchema = false)
abstract class GistDatabase : RoomDatabase() {

    abstract val gistDao: GistDao

    abstract val fileDao: FileDao

}
