package com.devlab74.projectone.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.devlab74.projectone.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        showMainFragment()
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "Main Fragment")
            .commit()
    }
}