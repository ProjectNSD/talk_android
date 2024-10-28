package com.nsd.talk.data.db.contact

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    // 모든 연락처 가져오기
    @Query("SELECT * FROM table_contact")
    fun getAll(): List<ContactEntity>

    // 연락처 저장 - 중복된 경우 대신 들어감
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contactEntity: ContactEntity)
}