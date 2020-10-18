package com.askominas.carfilteringapp.carlist.ui

import SparkCar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.askominas.carfilteringapp.R

class CarListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val carImage: ImageView = itemView.findViewById(R.id.image_car)
    private val carModel: TextView = itemView.findViewById(R.id.text_car_model)
    private val carPlate: TextView = itemView.findViewById(R.id.text_car_plate)
    private val carAddress: TextView = itemView.findViewById(R.id.text_car_address)
    private val carBattery: TextView = itemView.findViewById(R.id.text_car_battery_value)

    fun bind(car: SparkCar) {
        carImage.load(car.model.photoUrl)
        carModel.text = car.model.title
        carPlate.text = car.plateNumber
        carAddress.text = car.location.address
        carBattery.text = car.batteryPercentage.toString()
    }
}
