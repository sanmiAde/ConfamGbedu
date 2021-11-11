package com.sanmidev.confamgbedu.domain.model

data class Gbedu(
    val gbeduId: GbeduId,
    val name: String,
    val artistName: String,
    val rating: Rating,
    val meta: Meta
)
