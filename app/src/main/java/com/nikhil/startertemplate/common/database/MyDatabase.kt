package com.nikhil.startertemplate.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.startertemplate.common.models.ModalData

@Database(entities = [ModalData::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDAO
}
