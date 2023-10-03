package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.ImageView

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)

    fun isUserShow(): Boolean {
        return pref.getBoolean(BOARD_KEY, false)
    }

    fun userShowed() {
        pref.edit().putBoolean(BOARD_KEY, true).apply()
    }

    fun saveName(name:String){
        pref.edit().putString(NAME_KEY,name).apply()
    }

    fun saveImage(image: String){
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getName():String?{
        return pref.getString(NAME_KEY,"")
    }

    fun getImage():String?{
        return pref.getString(IMAGE_KEY, "")
    }


    companion object {
        const val PREF_KEY = "pref.key"
        const val BOARD_KEY = "board.key"
        const val NAME_KEY = "image.key"
        const val IMAGE_KEY = "name.key"
    }

}