package com.askominas.carfilteringapp.models

import com.google.gson.annotations.SerializedName

data class SparkCar (
    @SerializedName("id") val id : Int,
    @SerializedName("plateNumber") val plateNumber : String,
    @SerializedName("location") val location : Location,
    @SerializedName("model") val model : Model,
    @SerializedName("batteryPercentage") val batteryPercentage : Int,
    @SerializedName("batteryEstimatedDistance") val batteryEstimatedDistance : Int,
    @SerializedName("isCharging") val isCharging : Boolean,
    @SerializedName("servicePlusEGoPoints") val servicePlusEGoPoints : Int
)