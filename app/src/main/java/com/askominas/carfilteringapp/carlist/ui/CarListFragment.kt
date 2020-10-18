package com.askominas.carfilteringapp.carlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.askominas.carfilteringapp.R
import com.askominas.carfilteringapp.base.BaseFragment
import com.askominas.carfilteringapp.carlist.viewmodels.CarListViewModel
import com.askominas.carfilteringapp.databinding.FragmentCarListBinding


class CarListFragment : BaseFragment<CarListViewModel, FragmentCarListBinding>() {
    override val layoutResourceID: Int
        get() = R.layout.fragment_car_list

    override val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CarListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val carListLayoutManager = LinearLayoutManager(context)
        val carListAdapter = CarListAdapter()

        binding.recyclerCarList.adapter = carListAdapter
        binding.recyclerCarList.layoutManager = carListLayoutManager

        viewModel.updateCarList.observe(viewLifecycleOwner, {
            carListAdapter.updateCarList(viewModel.carList)
        })

        viewModel.sortByDistanceEvent.observe(viewLifecycleOwner, {
            val shouldSort = requestLocationPermission(
                onGranted = { viewModel.initializeCarList() },
                errorID = R.string.car_list_permission_error
            )
            if (!shouldSort)
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

        requestLocationPermission(
            onGranted = { viewModel.initializeCarList() },
            errorID = R.string.car_list_permission_error
        )
        return view
    }
}
