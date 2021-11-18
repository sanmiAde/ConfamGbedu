package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksState

data class GbeduState(
    val name: String = "",
    val artistName: String = "",
) : MavericksState {
    val isValid = name.isNotBlank() && artistName.isNotBlank()
}