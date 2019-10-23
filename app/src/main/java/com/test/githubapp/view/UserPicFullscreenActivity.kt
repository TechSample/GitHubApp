package com.test.githubapp.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.test.githubapp.R
import kotlinx.android.synthetic.main.view_user_timeline_pic.*

class UserPicFullscreenActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_user_timeline_pic)
        setTitle(getString(R.string.user_detail))
        if(intent.getStringExtra("user_avatar_url")!= null){

            Glide.with(this)
                .asBitmap()
                .load(intent.getStringExtra("user_avatar_url"))
                .into(object:SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imgUserTimelinePic.setImageBitmap(resource)
                    }


                })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.delete_menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){

            getString(R.string.delete) -> {

                Toast.makeText(this,getString(R.string.user_deleted),Toast.LENGTH_SHORT).show()
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        return super.onContextItemSelected(item)
    }
}

