package com.sanmidev.confamgbedu.ui.gdebuList

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.sanmidev.confamgbedu.data.local.GbeduDao
import com.sanmidev.confamgbedu.di.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class GbeduListViewModel(initialState: GbeduListState, private val gbeduDao: GbeduDao) :
    MavericksViewModel<GbeduListState>(initialState) {

    init {
        getGbeduList()
    }

    private fun getGbeduList() {
        gbeduDao.getGbedus().map { list -> list.map { it.toDomain() } }.execute(Dispatchers.IO) {
            copy(
                gbeduListRequest = it
            )
        }
    }


    /**
     * If you implement MvRxViewModelFactory in your companion object, MvRx will use that to create
     * your ViewModel. You can use this to achieve constructor dependency injection with Mavericks.
     *
     * @see MavericksViewModelFactory
     */
    companion object : MavericksViewModelFactory<GbeduListViewModel, GbeduListState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: GbeduListState
        ): GbeduListViewModel {
            val gbeduDao = Graph.database.gbeduDao()
            return GbeduListViewModel(state, gbeduDao)
        }
    }
}