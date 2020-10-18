package com.askominas.carfilteringapp.carlist.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.askominas.carfilteringapp.models.SparkCar
import com.askominas.carfilteringapp.networking.SparkCarApi
import com.askominas.carfilteringapp.utils.SingleLiveEvent
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class CarListViewModel @Inject constructor(val retrofit: Retrofit, val rxLocation: RxLocation) :
    ViewModel() {

    val sortByDistanceEvent = SingleLiveEvent<Boolean>()
    val sortByPlateEvent = SingleLiveEvent<Boolean>()
    val sortByBatteryEvent = SingleLiveEvent<Boolean>()
    val updateCarList = SingleLiveEvent<Boolean>()

    var currentLat: Double = 0.0
    var currentLon: Double = 0.0
    var carList: List<SparkCar> = arrayListOf()
    var isCarListInitialized = false

    @SuppressLint("MissingPermission", "CheckResult")
    fun loadCarList() {
        if (isCarListInitialized)
            return
        isCarListInitialized = true
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

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
        rxLocation.location().updates(locationRequest)
            .flatMap { location ->
                rxLocation.geocoding().fromLocation(location).toObservable()
            }
            .subscribe { address ->
                currentLat = address.latitude
                currentLon = address.longitude
            }
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
        carList = carList.sortedWith(compareBy {
            it.distanceToCurrentLocation = distanceBetween(
                currentLat,
                currentLon,
                it.location.latitude,
                it.location.longitude
            )
            it.distanceToCurrentLocation
        })
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

    private fun distanceBetween(
        fromLat: Double,
        fromLon: Double,
        toLat: Double,
        toLon: Double
    ): Double {
        val radius = 6371.0
        val deltaLat = Math.toRadians(toLat - fromLat)
        val deltaLon = Math.toRadians(toLon - fromLon)
        val lat1 = Math.toRadians(fromLat)
        val lat2 = Math.toRadians(toLat)
        val aVal = sin(deltaLat / 2) * sin(deltaLat / 2) +
                sin(deltaLon / 2) * sin(deltaLon / 2) * cos(lat1) * cos(lat2)
        val cVal = 2 * atan2(sqrt(aVal), sqrt(1 - aVal))
        val distance = radius * cVal
        Log.d("distance", "radius * angle = $distance")
        return distance
    }
}
