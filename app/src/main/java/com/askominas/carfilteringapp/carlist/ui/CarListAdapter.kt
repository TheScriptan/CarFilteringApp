package com.askominas.carfilteringapp.carlist.ui

import SparkCar
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarListAdapter : RecyclerView.Adapter<CarListViewHolder>() {

    private val carList: ArrayList<SparkCar> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = carList.size
}