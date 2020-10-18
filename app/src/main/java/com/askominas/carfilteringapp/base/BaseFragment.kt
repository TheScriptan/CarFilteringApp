package com.askominas.carfilteringapp.base

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.askominas.carfilteringapp.BR
import com.tbruyelle.rxpermissions3.RxPermissions
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, Binding : ViewDataBinding>() :
    DaggerFragment() {

    abstract val layoutResourceID: Int
    abstract val viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: Binding
    private lateinit var rxPermissions: RxPermissions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceID, container, false)
        val view = binding.root
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        rxPermissions = RxPermissions(this)
        return view
    }

    protected fun requestLocationPermission(onGranted: () -> Unit, errorID: Int): Boolean {
        var isPermission = false
        if (!rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
            !rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            rxPermissions.requestEachCombined(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).subscribe { permission ->
                if (permission.granted) {
                    onGranted()
                    isPermission = true
                } else {
                    Toast.makeText(
                        context,
                        getString(errorID),
                        Toast.LENGTH_LONG
                    ).show()
                    isPermission = false
                }
            }
        } else {
            isPermission = true
            onGranted()
        }
        return isPermission
    }
}
