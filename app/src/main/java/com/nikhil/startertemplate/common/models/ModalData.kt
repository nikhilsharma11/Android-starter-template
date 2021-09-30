package com.nikhil.startertemplate.common.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "my_table")
class ModalData {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var name: String? = null
    var email: String? = null
    var country: String? = null

    @Ignore
    constructor() {}

    constructor(
        name: String?,
        email: String?,
        country: String?
    ) {
        this.name = name
        this.email = email
        this.country = country
    }

}
