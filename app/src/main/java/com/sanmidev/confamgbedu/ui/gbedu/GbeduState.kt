package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.sanmidev.confamgbedu.data.local.GbeduEntity
import com.sanmidev.confamgbedu.domain.model.Rating
import com.sanmidev.confamgbedu.domain.model.ReleaseType

data class GbeduState(
    val name: String = "",
    val artistName: String = "",
    val rating: Rating = Rating.ZERO,
    val selectedReleaseType: ReleaseType = ReleaseType.SINGLE,
    val storeGbeduRequest: Async<Unit> = Uninitialized,
    //todo move this to an edit detail screen. Managing edit state and create gbedu state is becoming difficult.
    val getGbeduRequest: Async<GbeduEntity> = Uninitialized
) : MavericksState {
    val isValid = name.isNotBlank() && artistName.isNotBlank() && rating != Rating.ZERO
    val availableReleaseTypes: List<ReleaseType> =
        listOf(ReleaseType.LP, ReleaseType.EP, ReleaseType.SINGLE)
}