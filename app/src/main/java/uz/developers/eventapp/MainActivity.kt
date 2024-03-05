package uz.developers.eventapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.developers.eventapp.databinding.ActivityMainBinding
import uz.developers.eventapp.pref.MyShared

class MainActivity : AppCompatActivity() {
    private val batteryReceiver = BroadcastReceiver()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                checkPermissions(
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.FOREGROUND_SERVICE,
                        Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK,
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.INTERNET
                    )
                ) {
                    // Handle permissions callback
                }
            }
        }


        setSwitchedSwitchers()

        val musicIntent = Intent(this, MusicService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(musicIntent)
        }
        this.startService(musicIntent)
        initListeners()
    }

    private fun setSwitchedSwitchers() {
        binding.switcherStartCharging.isActivated = MyShared.getSwitcher(Intent.ACTION_POWER_CONNECTED) == 1

        binding.switcherEndCharging.isActivated = MyShared.getSwitcher(Intent.ACTION_POWER_DISCONNECTED) == 1

        binding.switcherBluetoothOn.isActivated = MyShared.getSwitcher(BluetoothAdapter.STATE_ON.toString()) == 1

        binding.switcherBluetoothOff.isActivated = MyShared.getSwitcher(BluetoothAdapter.STATE_OFF.toString()) == 1

        binding.switcherAirplaneOff.isActivated = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}off") == 1

        binding.switcherAirplaneOn.isActivated = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}on") == 1

        binding.switcherLowBattery.isActivated = MyShared.getSwitcher(Intent.ACTION_BATTERY_LOW) == 1

        binding.switcherFullBattery.isActivated = MyShared.getSwitcher(Intent.ACTION_BATTERY_OKAY) == 1
    }

    private fun initListeners() {
//
//        binding.switcherWifiCon.setOnClickListener {
//            val switcher = MyShared.getSwitcher("android.net.wifi.STATE_CHANGECON")
//            if (switcher == 0) {
//                MyShared.setSwitcher("android.net.wifi.STATE_CHANGECON", ActionEnum.SWITCH_ON)
//                binding.switcherWifiCon.isActivated = true
//            } else {
//                MyShared.setSwitcher("android.net.wifi.STATE_CHANGECON", ActionEnum.SWITCH_OFF)
//                binding.switcherWifiCon.isActivated = false
//            }
//
//        }
//        binding.switcherWifiDiscon.setOnClickListener {
//            val switcher = MyShared.getSwitcher("android.net.wifi.STATE_CHANGEDISCON")
//            if (switcher == 0) {
//                MyShared.setSwitcher("android.net.wifi.STATE_CHANGEDISCON", ActionEnum.SWITCH_ON)
//                binding.switcherWifiDiscon.isActivated = true
//            } else {
//                MyShared.setSwitcher("android.net.wifi.STATE_CHANGEDISCON", ActionEnum.SWITCH_OFF)
//                binding.switcherWifiDiscon.isActivated = false
//            }
//        }

        binding.switcherStartCharging.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_POWER_CONNECTED)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_POWER_CONNECTED, ActionEnum.SWITCH_ON)
                binding.switcherStartCharging.isActivated = true
            } else {
                MyShared.setSwitcher(Intent.ACTION_POWER_CONNECTED, ActionEnum.SWITCH_OFF)
                binding.switcherStartCharging.isActivated = false
            }
        }
        binding.switcherEndCharging.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_POWER_DISCONNECTED)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_POWER_DISCONNECTED, ActionEnum.SWITCH_ON)
                binding.switcherEndCharging.isActivated = true
            } else {
                MyShared.setSwitcher(Intent.ACTION_POWER_DISCONNECTED, ActionEnum.SWITCH_OFF)
                binding.switcherEndCharging.isActivated = false
            }
        }
        binding.switcherBluetoothOn.setOnClickListener {
            val switcher = MyShared.getSwitcher(BluetoothAdapter.STATE_ON.toString())
            if (switcher == 0) {
                MyShared.setSwitcher(BluetoothAdapter.STATE_ON.toString(), ActionEnum.SWITCH_ON)
                binding.switcherBluetoothOn.isActivated = true
            } else {
                MyShared.setSwitcher(BluetoothAdapter.STATE_ON.toString(), ActionEnum.SWITCH_OFF)
                binding.switcherBluetoothOn.isActivated = false
            }
        }
        binding.switcherBluetoothOff.setOnClickListener {
            val switcher = MyShared.getSwitcher(BluetoothAdapter.STATE_OFF.toString())
            if (switcher == 0) {
                MyShared.setSwitcher(BluetoothAdapter.STATE_OFF.toString(), ActionEnum.SWITCH_ON)
                binding.switcherBluetoothOff.isActivated = true
            } else {
                MyShared.setSwitcher(BluetoothAdapter.STATE_OFF.toString(), ActionEnum.SWITCH_OFF)
                binding.switcherBluetoothOff.isActivated = false
            }
        }
//        binding.switcherTimezoneChanged.setOnClickListener {
//            val switcher = MyShared.getSwitcher(Intent.ACTION_TIMEZONE_CHANGED)
//            if (switcher == 0) {
//                MyShared.setSwitcher(Intent.ACTION_TIMEZONE_CHANGED, ActionEnum.SWITCH_ON)
//                binding.switcherTimezoneChanged.isActivated = true
//            } else {
//                MyShared.setSwitcher(Intent.ACTION_TIMEZONE_CHANGED, ActionEnum.SWITCH_OFF)
//                binding.switcherTimezoneChanged.isActivated = false
//            }
//        }
        binding.switcherAirplaneOff.setOnClickListener {
            val switcher = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}off")
            if (switcher == 0) {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}off",
                    ActionEnum.SWITCH_ON
                )
                binding.switcherAirplaneOff.isActivated = true
            } else {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}off",
                    ActionEnum.SWITCH_OFF
                )
                binding.switcherAirplaneOff.isActivated = false
            }
        }
        binding.switcherAirplaneOn.setOnClickListener {
            val switcher = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}on")
            if (switcher == 0) {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}on",
                    ActionEnum.SWITCH_ON
                )
                binding.switcherAirplaneOn.isActivated = true
            } else {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}on",
                    ActionEnum.SWITCH_OFF
                )
                binding.switcherAirplaneOn.isActivated = false
            }
        }
//        binding.switcherScreenOff.setOnClickListener {
//            val switcher = MyShared.getSwitcher(Intent.ACTION_SCREEN_OFF)
//            if (switcher == 0) {
//                MyShared.setSwitcher(Intent.ACTION_SCREEN_OFF, ActionEnum.SWITCH_ON)
//                binding.switcherScreenOff.isActivated = true
//            } else {
//                MyShared.setSwitcher(Intent.ACTION_SCREEN_OFF, ActionEnum.SWITCH_OFF)
//                binding.switcherScreenOff.isActivated = false
//            }
//        }
        binding.switcherLowBattery.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_BATTERY_LOW)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_LOW, ActionEnum.SWITCH_ON)
                binding.switcherLowBattery.isActivated = true
            } else {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_LOW, ActionEnum.SWITCH_OFF)
                binding.switcherLowBattery.isActivated = false
            }
        }
        binding.switcherFullBattery.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_BATTERY_OKAY)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_OKAY, ActionEnum.SWITCH_ON)
                binding.switcherFullBattery.isActivated = true
            } else {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_OKAY, ActionEnum.SWITCH_OFF)
                binding.switcherFullBattery.isActivated = false
            }
        }
//        binding.switcherScreenOn.setOnClickListener {
//            val switcher = MyShared.getSwitcher(Intent.ACTION_SCREEN_ON)
//            if (switcher == 0) {
//                MyShared.setSwitcher(Intent.ACTION_SCREEN_ON, ActionEnum.SWITCH_ON)
//                binding.switcherScreenOn.isActivated = true
//            } else {
//                MyShared.setSwitcher(Intent.ACTION_SCREEN_ON, ActionEnum.SWITCH_OFF)
//                binding.switcherScreenOn.isActivated = false
//            }
//        }
    }

    private fun registerReceiver() : IntentFilter {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
        intentFilter.addAction(Intent.ACTION_CALL)
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED)
//        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
//        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
//        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED")
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED")
        intentFilter.addAction("android.net.wifi.STATE_CHANGE")
        return intentFilter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}