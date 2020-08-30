package com.sergio.gistapp.gist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(gist: GistEntity)

    @Delete
    fun delete(gist: GistEntity)

    @Transaction
    @Query("SELECT * FROM gist_table")
    fun getAllGistsWithFiles(): LiveData<List<GistWithFiles>>

    @Query("SELECT * FROM gist_table")
    fun getAllGists(): List<GistEntity>
}