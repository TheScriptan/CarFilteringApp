package com.askominas.carfilteringapp.carlist.ui

import com.askominas.carfilteringapp.models.SparkCar
import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.askominas.carfilteringapp.R
import com.askominas.carfilteringapp.databinding.FragmentCarListBinding
import com.google.gson.Gson
import com.tbruyelle.rxpermissions3.RxPermissions


class CarListFragment : Fragment() {

    private lateinit var binding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false)
        binding.lifecycleOwner = this

        val view = binding.root
        val rxPermissions = RxPermissions(this)

        val carListLayoutManager = LinearLayoutManager(context)
        val carListAdapter = CarListAdapter()

        val json = "[{\n" +
                "    \"id\": 31,\n" +
                "    \"plateNumber\": \"TEST31\",\n" +
                "    \"location\": {\n" +
                "        \"id\": 0,\n" +
                "        \"latitude\": 54.66855,\n" +
                "        \"longitude\": 25.23474,\n" +
                "        \"address\": \"Laisvės prospektas 1\"\n" +
                "    },\n" +
                "    \"model\": {\n" +
                "        \"id\": 7,\n" +
                "        \"title\": \"Nissan Leaf\",\n" +
                "        \"photoUrl\": \"https://s3-eu-west-1.amazonaws.com/rideshareuploads/uploads/nissanLeaf.jpg\",\n" +
                "        \"loyaltyPrize\": 0,\n" +
                "        \"rate\": {\n" +
                "            \"isWeekend\": true,\n" +
                "            \"currency\": \"Euro\",\n" +
                "            \"currencySymbol\": \"€\",\n" +
                "            \"lease\": {\n" +
                "                \"workdays\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 25.0,\n" +
                "                    \"minimumPrice\": 1.50,\n" +
                "                    \"minimumMinutes\": 10\n" +
                "                },\n" +
                "                \"weekends\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 25.0,\n" +
                "                    \"minimumPrice\": 1.50,\n" +
                "                    \"minimumMinutes\": 10\n" +
                "                },\n" +
                "                \"kilometerPrice\": 0.0,\n" +
                "                \"freeKilometersPerDay\": 0\n" +
                "            },\n" +
                "            \"reservation\": {\n" +
                "                \"initialPrice\": 0.0,\n" +
                "                \"initialMinutes\": 15,\n" +
                "                \"extensionPrice\": 5.0,\n" +
                "                \"extensionMinutes\": 15,\n" +
                "                \"longerExtensionPrice\": 5.0,\n" +
                "                \"longerExtensionMinutes\": 30\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"batteryPercentage\": 85,\n" +
                "    \"batteryEstimatedDistance\": 152.0,\n" +
                "    \"isCharging\": false,\n" +
                "    \"servicePlusEGoPoints\": 0\n" +
                "}, {\n" +
                "    \"id\": 28,\n" +
                "    \"plateNumber\": \"TEST28\",\n" +
                "    \"location\": {\n" +
                "        \"id\": 0,\n" +
                "        \"latitude\": 54.70006000,\n" +
                "        \"longitude\": 25.26496000,\n" +
                "        \"address\": \"Lvovo gatvė 62\"\n" +
                "    },\n" +
                "    \"model\": {\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"VW e-Up!\",\n" +
                "        \"photoUrl\": \"https://s3-eu-west-1.amazonaws.com/rideshareuploads/uploads/vweUp.jpg\",\n" +
                "        \"loyaltyPrize\": 0,\n" +
                "        \"rate\": {\n" +
                "            \"isWeekend\": true,\n" +
                "            \"currency\": \"Euro\",\n" +
                "            \"currencySymbol\": \"€\",\n" +
                "            \"lease\": {\n" +
                "                \"workdays\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 28.0,\n" +
                "                    \"minimumPrice\": 1.95,\n" +
                "                    \"minimumMinutes\": 13\n" +
                "                },\n" +
                "                \"weekends\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 20.0,\n" +
                "                    \"minimumPrice\": 1.95,\n" +
                "                    \"minimumMinutes\": 13\n" +
                "                },\n" +
                "                \"kilometerPrice\": 0.1,\n" +
                "                \"freeKilometersPerDay\": 200\n" +
                "            },\n" +
                "            \"reservation\": {\n" +
                "                \"initialPrice\": 2.0,\n" +
                "                \"initialMinutes\": 3,\n" +
                "                \"extensionPrice\": 4.0,\n" +
                "                \"extensionMinutes\": 1,\n" +
                "                \"longerExtensionPrice\": 2.0,\n" +
                "                \"longerExtensionMinutes\": 2\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"batteryPercentage\": 99,\n" +
                "    \"batteryEstimatedDistance\": 150.0,\n" +
                "    \"isCharging\": false,\n" +
                "    \"servicePlusEGoPoints\": 0\n" +
                "}, {\n" +
                "    \"id\": 5,\n" +
                "    \"plateNumber\": \"TEST5\",\n" +
                "    \"location\": {\n" +
                "        \"id\": 0,\n" +
                "        \"latitude\": 54.56028,\n" +
                "        \"longitude\": 25.15663,\n" +
                "        \"address\": \"Unnamed Road\"\n" +
                "    },\n" +
                "    \"model\": {\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"VW e-Up!\",\n" +
                "        \"photoUrl\": \"https://s3-eu-west-1.amazonaws.com/rideshareuploads/uploads/vweUp.jpg\",\n" +
                "        \"loyaltyPrize\": 0,\n" +
                "        \"rate\": {\n" +
                "            \"isWeekend\": true,\n" +
                "            \"currency\": \"Euro\",\n" +
                "            \"currencySymbol\": \"€\",\n" +
                "            \"lease\": {\n" +
                "                \"workdays\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 28.0,\n" +
                "                    \"minimumPrice\": 1.95,\n" +
                "                    \"minimumMinutes\": 13\n" +
                "                },\n" +
                "                \"weekends\": {\n" +
                "                    \"amount\": 0.15,\n" +
                "                    \"minutes\": 1,\n" +
                "                    \"dailyAmount\": 20.0,\n" +
                "                    \"minimumPrice\": 1.95,\n" +
                "                    \"minimumMinutes\": 13\n" +
                "                },\n" +
                "                \"kilometerPrice\": 0.1,\n" +
                "                \"freeKilometersPerDay\": 200\n" +
                "            },\n" +
                "            \"reservation\": {\n" +
                "                \"initialPrice\": 2.0,\n" +
                "                \"initialMinutes\": 3,\n" +
                "                \"extensionPrice\": 4.0,\n" +
                "                \"extensionMinutes\": 1,\n" +
                "                \"longerExtensionPrice\": 2.0,\n" +
                "                \"longerExtensionMinutes\": 2\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"batteryPercentage\": 69,\n" +
                "    \"batteryEstimatedDistance\": 97.0,\n" +
                "    \"isCharging\": false,\n" +
                "    \"servicePlusEGoPoints\": 0\n" +
                "}]"
        val sparkList: Array<SparkCar> = Gson().fromJson(
            json,
            Array<SparkCar>::class.java
        )


        binding.recyclerCarList.adapter = carListAdapter
        binding.recyclerCarList.layoutManager = carListLayoutManager

        rxPermissions
            .request(Manifest.permission.ACCESS_FINE_LOCATION)
            .subscribe { granted: Boolean ->
                if (granted) {
                    carListAdapter.initializeCarList(sparkList.asList())
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
