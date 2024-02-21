package com.shilei.ijk.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view_1, FirstFragment()).addToBackStack("fragment1")
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view_2, SecondFragment()).addToBackStack("fragment2")
            .commit()
    }
}