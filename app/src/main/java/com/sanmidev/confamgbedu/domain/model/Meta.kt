package com.sanmidev.confamgbedu.domain.model

import kotlinx.datetime.LocalDateTime

data class Meta(
    val releaseType: ReleaseType,
    val releaseDate: LocalDateTime,
    val createdTimeStamp: LocalDateTime
)
