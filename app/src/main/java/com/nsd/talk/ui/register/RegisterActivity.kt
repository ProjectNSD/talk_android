package com.nsd.talk.ui.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nsd.talk.databinding.ActivityRegisterBinding
import com.nsd.talk.ui.main.MainActivity


class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val REQUEST_PERMISSIONS = 1
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        isFirstStartApp()
        setupUi()
    }

    private fun isFirstStartApp() {
        if(!viewModel.isFirstStartApp(applicationContext)) {
            viewModel.setFirstStartAppPreference(applicationContext)
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }
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
            checkPermission()
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

    private fun checkPermission() {
        val permission = mutableMapOf<String, String>()
        permission["readContacts"] = Manifest.permission.READ_CONTACTS
        requestPermissions(permission.values.toTypedArray(), REQUEST_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {
            /* 2. 권한 요청을 거부했다면 안내 메시지 보여주며 앱 종료 */
            grantResults.forEach {
                if (it == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(
                        applicationContext,
                        "서비스의 필요한 권한입니다.\n권한에 동의해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}