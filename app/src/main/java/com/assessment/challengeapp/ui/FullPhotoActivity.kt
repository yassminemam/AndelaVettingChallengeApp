package com.assessment.challengeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.challengeapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_photo.*

class FullPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_photo)
        val url = intent.getStringExtra("URL")
        Picasso.with(this).load(url).fit().centerCrop()
            .into(iv_full_photo)
    }
}
