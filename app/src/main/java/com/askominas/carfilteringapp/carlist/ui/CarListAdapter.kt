package com.askominas.carfilteringapp.carlist.ui

import com.askominas.carfilteringapp.models.SparkCar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.askominas.carfilteringapp.R

class CarListAdapter : RecyclerView.Adapter<CarListViewHolder>() {

    private val carList: ArrayList<SparkCar> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_car_item, parent, false)
        return CarListViewHolder(view)
    }


    override fun onBindViewHolder(holder: CarListViewHolder, pos: Int) {
        holder.bind(carList[pos])
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun initializeCarList(list: List<SparkCar>) {
        carList.addAll(list)
        notifyDataSetChanged()
    }
}
