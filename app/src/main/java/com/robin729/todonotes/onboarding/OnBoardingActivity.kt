package com.robin729.todonotes.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robin729.todonotes.R
import com.robin729.todonotes.utils.AppConstants
import com.robin729.todonotes.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity(), OnBoardingOneFragment.OnNextClick,
    OnBoardingTwoFragment.OnOptionClick {

    private val sharedPreferences: SharedPreferences by lazy{
        getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun onClick() {
        viewPager.currentItem = 1
    }

    override fun onNextClick() {
        editor.putBoolean(AppConstants.ON_BOARDED_SUCCESSFULLY, true)
        editor.apply()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackClick() {
        viewPager.currentItem = 0
    }
}
