package com.askominas.carfilteringapp.carlist.ui

import android.Manifest
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
import com.tbruyelle.rxpermissions3.RxPermissions
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CarListFragment : DaggerFragment() {

    private lateinit var binding: FragmentCarListBinding
    private lateinit var rxPermissions: RxPermissions

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CarListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        rxPermissions = RxPermissions(this)
        val view = binding.root

        val carListLayoutManager = LinearLayoutManager(context)
        val carListAdapter = CarListAdapter()

        binding.recyclerCarList.adapter = carListAdapter
        binding.recyclerCarList.layoutManager = carListLayoutManager

        viewModel.updateCarList.observe(viewLifecycleOwner, {
            carListAdapter.updateCarList(viewModel.carList)
        })

        viewModel.sortByDistanceEvent.observe(viewLifecycleOwner, {
            if (!requestLocationPermission())
                return@observe
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

        requestLocationPermission()
        return view
    }

    private fun requestLocationPermission(): Boolean {
        var isPermission = false
        if (!rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
            !rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            rxPermissions.requestEachCombined(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).subscribe { permission ->
                if (permission.granted) {
                    viewModel.loadCarList()
                    isPermission = true
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.car_list_permission_error),
                        Toast.LENGTH_LONG
                    ).show()
                    isPermission = false
                }
            }
        } else {
            isPermission = true
            viewModel.loadCarList()
        }
        return isPermission
    }
}
