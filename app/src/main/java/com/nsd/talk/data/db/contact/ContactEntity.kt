package com.nsd.talk.data.db.contact

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_contact")
data class ContactEntity(
    @PrimaryKey
    val phoneNumber: String,
    val name: String,
    val profile: String,
)
