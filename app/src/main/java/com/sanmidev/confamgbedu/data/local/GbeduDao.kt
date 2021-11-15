package com.sanmidev.confamgbedu.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GbeduDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGbedu(gbeduEntity: GbeduEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGbedu(gbeduEntity: GbeduEntity)

    @Query("SELECT * FROM gbedu")
    suspend fun getGbedus(): List<GbeduEntity>
}