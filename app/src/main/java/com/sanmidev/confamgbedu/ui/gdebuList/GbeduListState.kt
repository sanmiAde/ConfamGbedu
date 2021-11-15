package com.sanmidev.confamgbedu.ui.gdebuList

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.sanmidev.confamgbedu.data.local.GbeduEntity
import com.sanmidev.confamgbedu.domain.model.Gbedu

data class GbeduListState(
    /** We use this request to store the list of all gbedu. */
    val gbeduList: List<Gbedu> = emptyList(),
    /** We use this Async to keep track of the state of the current network request. */
    val gbeduListRequest: Async<List<GbeduEntity>> = Uninitialized,
) : MavericksState