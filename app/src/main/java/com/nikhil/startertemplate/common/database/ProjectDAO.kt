package com.nikhil.startertemplate.common.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.startertemplate.common.models.ModalData

@Dao
interface ProjectDAO {
    @Insert
    suspend fun insert(modalData: ModalData)

    @Query("SELECT * FROM my_table")
    suspend fun getAllData(): List<ModalData?>

    @Delete
    suspend fun delete(modalData: ModalData)
}