package com.example.citypulse

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.citypulse.databinding.ActivitySplashBinding
import com.example.citypulse.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * SplashActivity premium gérant l'accueil de l'utilisateur.
 * Gère la redirection vers l'Onboarding ou la MainActivity.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appearAnim = AnimationUtils.loadAnimation(this, R.anim.logo_appear)
        binding.logoContainer.startAnimation(appearAnim)

        lifecycleScope.launch {
            delay(2000)
            checkOnboardingAndNavigate()
        }
    }

    /**
     * Vérifie si l'onboarding a déjà été complété.
     */
    private fun checkOnboardingAndNavigate() {
        val sharedPref = getSharedPreferences("citypulse_prefs", Context.MODE_PRIVATE)
        val onboardingFinished = sharedPref.getBoolean("onboarding_finished", false)

        val destination = if (onboardingFinished) {
            MainActivity::class.java
        } else {
            OnboardingActivity::class.java
        }

        startActivity(Intent(this, destination))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
