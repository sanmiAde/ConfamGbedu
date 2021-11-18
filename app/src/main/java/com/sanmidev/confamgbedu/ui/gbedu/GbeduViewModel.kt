package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksViewModel

class GbeduViewModel(gbeduState: GbeduState) : MavericksViewModel<GbeduState>(gbeduState) {

    fun updateGbeduName(name: String) {
        setState { copy(name = name) }
    }

    fun updateArtistName(name: String) {
        setState { copy(artistName = name) }
    }
}