package com.sanmidev.confamgbedu.data.local

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface GbeduDao {
    @Insert
    suspend fun insertGbedu(gbeduEntity: GbeduEntity)

    @Update
    suspend fun updateGbedu(gbeduEntity: GbeduEntity)

    @Query("SELECT * FROM gbedu")
    fun getGbedus(): Flow<List<GbeduEntity>>
}