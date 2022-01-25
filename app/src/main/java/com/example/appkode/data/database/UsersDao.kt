package com.example.appkode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entityPersonal: List<UsersEntity>)

    @Query("SELECT * FROM users_table WHERE department LIKE '%' || :dep || '%' Order by lastName ASC")
    fun sortByDepartment(dep: String): Flow<List<UsersEntity>>

    @Query("SELECT * FROM users_table WHERE lastName LIKE '%' || :query || '%' and department LIKE '%' || :dep || '%' Order by lastName ASC")
    fun searchUsers(dep: String,query: String): Flow<List<UsersEntity>>
}
