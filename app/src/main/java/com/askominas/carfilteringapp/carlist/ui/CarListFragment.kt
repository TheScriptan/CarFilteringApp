package com.askominas.carfilteringapp.carlist.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.askominas.carfilteringapp.BR
import com.askominas.carfilteringapp.R
import com.askominas.carfilteringapp.carlist.viewmodels.CarListViewModel
import com.askominas.carfilteringapp.databinding.FragmentCarListBinding
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import com.tbruyelle.rxpermissions3.RxPermissions
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CarListFragment : DaggerFragment() {

    private lateinit var binding: FragmentCarListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CarListViewModel::class.java)
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        val view = binding.root
        val rxPermissions = RxPermissions(this)
        val rxLocation = RxLocation(requireContext())

        val carListLayoutManager = LinearLayoutManager(context)
        val carListAdapter = CarListAdapter()

        binding.recyclerCarList.adapter = carListAdapter
        binding.recyclerCarList.layoutManager = carListLayoutManager

        viewModel.updateCarList.observe(viewLifecycleOwner, {
            carListAdapter.updateCarList(viewModel.carList)
        })

        viewModel.sortByDistanceEvent.observe(viewLifecycleOwner, {
            val sortedList = viewModel.sortByDistance()
            carListAdapter.updateCarList(sortedList)
        })

        viewModel.sortByPlateEvent.observe(viewLifecycleOwner, {
            val sortedList = viewModel.sortByPlate()
            carListAdapter.updateCarList(sortedList)
        })

        viewModel.sortByBatteryEvent.observe(viewLifecycleOwner, {
            val sortedList = viewModel.sortByBattery()
            carListAdapter.updateCarList(sortedList)
        })

        rxPermissions
            .requestEachCombined(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe { permission ->
                if (permission.granted) {
                    viewModel.loadCarList()
                    val locationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(5000)
                    rxLocation.location().updates(locationRequest)
                        .flatMap { location ->
                            rxLocation.geocoding().fromLocation(location).toObservable()
                        }
                        .subscribe { address ->
                            viewModel.currentLat = address.latitude
                            viewModel.currentLon = address.longitude
                        }
                } else {
                    Toast.makeText(
                        context,
                        "Location permission denied. Can't compare distance from cars",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        return view
    }
}
