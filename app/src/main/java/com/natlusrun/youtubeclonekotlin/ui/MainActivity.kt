package com.natlusrun.youtubeclonekotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.utils.NetworkConnection

class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        chekNetworkConnection()

    }

    private fun chekNetworkConnection() {
        val networkConnection = NetworkConnection(applicationContext)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.youtubeNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        networkConnection.observe(this, Observer { isConnected ->
            if (isConnected) {
                navController.navigate(R.id.playlistFragment)
            } else {
                navController.navigate(R.id.connectionFragment)
            }
        })
    }


}