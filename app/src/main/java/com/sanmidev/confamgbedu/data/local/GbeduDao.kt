package com.sanmidev.confamgbedu.data.local

import androidx.room.*
import com.sanmidev.confamgbedu.domain.model.GbeduId
import kotlinx.coroutines.flow.Flow

@Dao
interface GbeduDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGbedu(gbeduEntity: GbeduEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGbedu(gbeduEntity: GbeduEntity)

    @Query("SELECT * FROM gbedu")
    fun getGbedus(): Flow<List<GbeduEntity>>

    @Query("SELECT * FROM gbedu WHERE id = :id")
    suspend fun getGbedu(id: Long): GbeduEntity
}