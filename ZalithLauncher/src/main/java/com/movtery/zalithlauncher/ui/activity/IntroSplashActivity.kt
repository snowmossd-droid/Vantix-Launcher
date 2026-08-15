package com.movtery.zalithlauncher.ui.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.movtery.zalithlauncher.InfoDistributor
import com.movtery.zalithlauncher.R

class IntroSplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_splash)

        findViewById<TextView>(R.id.app_name_text).text = InfoDistributor.APP_NAME

        val cubeImage = findViewById<ImageView>(R.id.cube_image)
        (cubeImage.drawable as? android.graphics.drawable.Animatable)?.start()

        val progressBar = findViewById<ProgressBar>(R.id.loading_bar)
        ObjectAnimator.ofInt(progressBar, "progress", 0, 100).apply {
            duration = INTRO_DURATION_MS
            interpolator = LinearInterpolator()
            start()
        }

        progressBar.postDelayed({
            if (!isFinishing) {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }, INTRO_DURATION_MS + 400L)
    }

    companion object {
        private const val INTRO_DURATION_MS = 1800L
    }
}
