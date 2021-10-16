package com.example.youtubeplayer

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException
import androidx.appcompat.app.AppCompatActivity;

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlaySingle.setOnClickListener(this)
        btnStandAlone.setOnClickListener(this)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#E12A55")))

    }

    override fun onClick(view: View?) {
        val intent = when(view?.id){
            R.id.btnPlaySingle-> Intent(this, YoutubeActivity::class.java)
            R.id.btnStandAlone->Intent(this, StandAloneActivity::class.java)
            else->throw IllegalArgumentException("Undefigned button clicked")
        }

        startActivity(intent)
    }
}