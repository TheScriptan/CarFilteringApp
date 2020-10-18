package com.askominas.carfilteringapp.carlist.viewmodels

import androidx.lifecycle.ViewModel
import com.askominas.carfilteringapp.utils.SingleLiveEvent
import javax.inject.Inject

class CarListViewModel @Inject constructor() : ViewModel() {

    val sortByDistanceEvent = SingleLiveEvent<Boolean>()
    val sortByPlateEvent = SingleLiveEvent<Boolean>()
    val sortByBatteryEvent = SingleLiveEvent<Boolean>()

    fun onSortByDistanceClick() {
        sortByDistanceEvent.call()
    }

    fun onSortByPlateClick() {
        sortByPlateEvent.call()
    }

    fun onSortByBatteryClick() {
        sortByBatteryEvent.call()
    }
}
