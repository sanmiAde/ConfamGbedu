package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksViewModel
import com.sanmidev.confamgbedu.domain.model.Rating
import com.sanmidev.confamgbedu.domain.model.ReleaseType

class GbeduViewModel(gbeduState: GbeduState) : MavericksViewModel<GbeduState>(gbeduState) {

    fun updateGbeduName(name: String) {
        setState { copy(name = name) }
    }

    fun updateArtistName(name: String) {
        setState { copy(artistName = name) }
    }

    fun updateGbeduRating(rating: Float) {
        setState { copy(rating = Rating.processUserRating(rating)) }
    }

    fun updateGbeduReleaseType(releaseType: ReleaseType) {
        setState { copy(selectedReleaseType = releaseType) }
    }
}