package com.example.stopwatchwithnav

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stopwatchwithnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    //resets the timer to zero and update UI
    private fun resetTimer() {
        stopCountdown() // Stop the timer if running
        time = 0.0 // Reset time to zero
        binding.timeTV.text = formatTime(time) // Update UI with zero time
    }

    // start or stops the timer based on its current state
    private fun toggleTimer() {
        if (timerStarted)
            stopCountdown() // If timer is running, stop it
        else
            startCountdown() // If timer is stopped, start it
    }

    // start the timer service and update UI accordingly
    private fun startCountdown() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time) // Pass time to service
        startService(serviceIntent) // Start the timer service
        binding.startStopButton.text = "Stop" // Update button text to indicate stopping
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_pause_24) // Change icon to pause
        timerStarted = true // Set timer state to started
    }

    // stop the timer service and update UI accordingly
    private fun stopCountdown() {
        stopService(serviceIntent) // Stop the timer service
        binding.startStopButton.text = "Start" // Update button text to indicate starting
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24) // Change icon to play
        timerStarted = false // Set timer state to stopped
    }

    // BroadcastReceiver to update timer UI
    private val updateTimeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0) // Get updated time from service
            binding.timeTV.text = formatTime(time) // Update UI with new time
        }
    }

    // Function to convert time in seconds to formatted string (hh:mm:ss)
    private fun formatTime(time: Double): String {
        val resultInt = time.roundToInt() // Round time to integer
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return createTimeString(hours, minutes, seconds) // Format time string
    }

    // Function to format time into string in the format (hh:mm:ss)
    private fun createTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)







}