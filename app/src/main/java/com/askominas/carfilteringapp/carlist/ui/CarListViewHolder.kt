package com.askominas.carfilteringapp.carlist.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.askominas.carfilteringapp.R
import com.askominas.carfilteringapp.models.SparkCar
import java.math.BigDecimal
import java.math.RoundingMode

class CarListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val carImage: ImageView = itemView.findViewById(R.id.image_car)
    private val carModel: TextView = itemView.findViewById(R.id.text_car_model)
    private val carPlate: TextView = itemView.findViewById(R.id.text_car_plate)
    private val carAddress: TextView = itemView.findViewById(R.id.text_car_address)
    private val carDistance: TextView = itemView.findViewById(R.id.text_car_distance)
    private val carBattery: TextView = itemView.findViewById(R.id.text_car_battery_value)

    fun bind(car: SparkCar) {
        carImage.load(car.model.photoUrl)
        carModel.text = car.model.title
        carPlate.text = car.plateNumber
        carAddress.text = car.location.address
        carBattery.text = car.batteryPercentage.toString()

        if (car.distanceToCurrentLocation == 0.0)
            return

        carDistance.visibility = View.VISIBLE
        val distance = BigDecimal(car.distanceToCurrentLocation).setScale(2, RoundingMode.HALF_EVEN)
        carDistance.text = distance.toString() + "km"
    }
}
