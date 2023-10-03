package com.example.taskmanager.ui.utils

import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.net.URL

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}