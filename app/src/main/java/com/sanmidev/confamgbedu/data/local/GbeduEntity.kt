package com.sanmidev.confamgbedu.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanmidev.confamgbedu.domain.model.*

@Entity(tableName = "gbedu")
data class GbeduEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val artistName: String,
    val rating: Rating,
    @Embedded
    val meta: Meta
) {
    fun toDomain(): Gbedu = Gbedu(
        gbeduId = GbeduId(id),
        name = name,
        artistName = artistName,
        rating = rating,
        meta = meta
    )

    companion object {
        fun fromDomain(gbedu: Gbedu) = GbeduEntity(
            id = gbedu.gbeduId.value,
            name = gbedu.name,
            artistName = gbedu.artistName,
            rating = gbedu.rating,
            meta = gbedu.meta
        )
    }
}
