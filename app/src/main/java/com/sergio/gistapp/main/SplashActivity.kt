package com.sergio.gistapp.main

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.sergio.gistapp.R
import com.sergio.gistapp.gist.GistActivity
import kotlinx.android.synthetic.main.activity_spash.*

const val SPLASH_DELAY: Long = 6000

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        animation()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,GistActivity::class.java))

            finish()
        }, SPLASH_DELAY)
    }

    private fun animation() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(appText, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.startDelay = 3000
        animator.start()
    }
}