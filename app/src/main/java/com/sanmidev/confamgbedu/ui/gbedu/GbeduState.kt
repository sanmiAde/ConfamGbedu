package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksState
import com.sanmidev.confamgbedu.domain.model.Rating

data class GbeduState(
    val name: String = "",
    val artistName: String = "",
    val rating: Rating = Rating.ZERO
) : MavericksState {
    val isValid = name.isNotBlank() && artistName.isNotBlank()
}