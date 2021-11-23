package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksState
import com.sanmidev.confamgbedu.domain.model.Rating
import com.sanmidev.confamgbedu.domain.model.ReleaseType

data class GbeduState(
    val name: String = "",
    val artistName: String = "",
    val rating: Rating = Rating.ZERO,
    val selectedReleaseType: ReleaseType = ReleaseType.SINGLE
) : MavericksState {
    val isValid = name.isNotBlank() && artistName.isNotBlank() && rating != Rating.ZERO
    val availableReleaseTypes: List<ReleaseType> =
        listOf(ReleaseType.LP, ReleaseType.EP, ReleaseType.SINGLE)
}