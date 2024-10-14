package com.nsd.talk.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.nsd.talk.databinding.ActivityRegisterBinding
import com.nsd.talk.ui.main.MainActivity

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() = with(binding) {
        fadeOutTitle()
        btnNext.setOnClickListener {
            if (etName.text.isBlank()) {
                tvNotice.visibility = View.VISIBLE
            } else {
                tvNotice.visibility = View.INVISIBLE
                llId.visibility = View.INVISIBLE
                llPhoneNumber.visibility = View.VISIBLE
                btnRegister.visibility = View.VISIBLE
                btnNext.visibility = View.INVISIBLE
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fadeOutTitle() = with(binding) {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 3000

        val titleAnimation = AnimationSet(false)
        titleAnimation.addAnimation(fadeIn)
        tvTitle.animation = titleAnimation
        tvTitleDetail.animation = titleAnimation
        tvTitleExplanation.animation = titleAnimation
    }
}