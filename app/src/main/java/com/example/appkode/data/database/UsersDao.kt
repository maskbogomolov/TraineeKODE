package com.example.appkode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appkode.util.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entityPersonal: List<UsersEntity>)

    @Query("SELECT * FROM users_table WHERE department LIKE '%' || :dep || '%' Order by lastName ASC")
    fun sortByDepartment(dep: String): Flow<List<UsersEntity>>

    @Query("SELECT * FROM users_table WHERE lastName LIKE '%' || :query || '%' and department LIKE '%' || :dep || '%' Order by lastName ASC")
    fun searchUsersWithNameFilter(dep: String,query: String): Flow<List<UsersEntity>>

    @Query("SELECT * FROM users_table WHERE lastName LIKE '%' || :query || '%' and department LIKE '%' || :dep || '%' ORDER BY SUBSTR(DATE('NOW'), 6) > SUBSTR(birthday, 6), SUBSTR(birthday, 6)")
    fun searchUsersWithBirthdayFilter(dep: String,query: String): Flow<List<UsersEntity>>

    fun searchUsersWithFilter(dep: String,query: String, sortOrder: SortOrder): Flow<List<UsersEntity>>{
        return when(sortOrder){
            SortOrder.BY_NAME -> searchUsersWithNameFilter(dep,query)
            SortOrder.BY_DATA -> searchUsersWithBirthdayFilter(dep, query)
        }
    }
}
