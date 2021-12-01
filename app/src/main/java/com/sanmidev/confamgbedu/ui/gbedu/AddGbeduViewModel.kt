package com.sanmidev.confamgbedu.ui.gbedu

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.sanmidev.confamgbedu.data.local.GbeduDao
import com.sanmidev.confamgbedu.data.local.GbeduEntity
import com.sanmidev.confamgbedu.di.Graph
import com.sanmidev.confamgbedu.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AddGbeduViewModel(gbeduState: GbeduState, private val gbeduDao: GbeduDao) :
    MavericksViewModel<GbeduState>(gbeduState) {

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

    //is this correct. SHould the state be in the viewmodel directly
    fun storeGbedu(gbeduState: GbeduState) {
        suspend {
            gbeduDao.insertGbedu(
                GbeduEntity.fromDomain(
                    gbedu = Gbedu(
                        gbeduId = GbeduId(0L),
                        gbeduState.name,
                        gbeduState.artistName,
                        gbeduState.rating,
                        Meta(
                            gbeduState.selectedReleaseType, Clock.System.now().toLocalDateTime(
                                TimeZone.UTC
                            ), Clock.System.now().toLocalDateTime(TimeZone.UTC)
                        )
                    )
                )
            )
        }.execute(Dispatchers.IO) {
            copy(storeGbeduRequest = it)
        }
    }

    fun getGbeduRequest(id: Long) {
        suspend { gbeduDao.getGbedu(id) }.execute(Dispatchers.IO) {
            copy(getGbeduRequest = it,)
        }
    }

    /**
     * If you implement MvRxViewModelFactory in your companion object, MvRx will use that to create
     * your ViewModel. You can use this to achieve constructor dependency injection with Mavericks.
     *
     * @see MavericksViewModelFactory
     */
    companion object : MavericksViewModelFactory<AddGbeduViewModel, GbeduState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: GbeduState
        ): AddGbeduViewModel {
            val gbeduDao = Graph.database.gbeduDao()
            return AddGbeduViewModel(state, gbeduDao)
        }
    }
}