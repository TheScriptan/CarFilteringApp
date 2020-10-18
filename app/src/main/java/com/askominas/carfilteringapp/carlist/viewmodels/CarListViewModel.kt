package com.askominas.carfilteringapp.carlist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.askominas.carfilteringapp.models.SparkCar
import com.askominas.carfilteringapp.networking.SparkCarApi
import com.askominas.carfilteringapp.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CarListViewModel @Inject constructor(val retrofit: Retrofit) : ViewModel() {

    val sortByDistanceEvent = SingleLiveEvent<Boolean>()
    val sortByPlateEvent = SingleLiveEvent<Boolean>()
    val sortByBatteryEvent = SingleLiveEvent<Boolean>()
    val updateCarList = SingleLiveEvent<Boolean>()

    var carList: List<SparkCar> = arrayListOf()

    fun loadCarList() {
        val api = retrofit.create(SparkCarApi::class.java)
        api.listRepos().enqueue(object : Callback<List<SparkCar>> {
            override fun onResponse(
                call: Call<List<SparkCar>>,
                response: Response<List<SparkCar>>
            ) {
                if (response.body() != null) {
                    carList = ArrayList(response.body()!!)
                    updateCarList.call()
                }
            }

            override fun onFailure(call: Call<List<SparkCar>>, t: Throwable) {
                Log.d("TEST", "Failure: ${t.message}")
            }
        })
    }

    fun onSortByDistanceClick() {
        sortByDistanceEvent.call()
    }

    fun onSortByPlateClick() {
        sortByPlateEvent.call()
    }

    fun onSortByBatteryClick() {
        sortByBatteryEvent.call()
    }

    fun sortByDistance(): List<SparkCar> {
        carList = carList.reversed()
        return carList
    }

    fun sortByPlate(): List<SparkCar> {
        carList = carList.sortedWith(compareBy { it.plateNumber })
        return carList
    }

    fun sortByBattery(): List<SparkCar> {
        carList = carList.sortedByDescending { it.batteryPercentage }
        return carList
    }
}
