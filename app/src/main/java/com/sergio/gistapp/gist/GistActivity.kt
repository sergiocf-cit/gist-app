package com.sergio.gistapp.gist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sergio.gistapp.R

class GistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist)
    }
}