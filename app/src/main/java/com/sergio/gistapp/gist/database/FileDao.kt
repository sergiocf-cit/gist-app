package com.sergio.gistapp.gist.database

import androidx.room.*
import com.sergio.gistapp.gist.model.Gist

@Dao
interface FileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(file: FileEntity)

    @Delete
    fun delete(file: FileEntity)

    @Query("DELETE FROM file_table where gist_id = :id")
    fun deleteAllFiles(id: String)
}