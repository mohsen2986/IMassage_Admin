package com.imassage.data.database.sharedPreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class Preferences(
        applicationContext: Context
){
    private val prefs = "prefs"
    private var preferences: SharedPreferences
    private var editor: SharedPreferences.Editor
    init{
        preferences = applicationContext.getSharedPreferences(prefs , Context.MODE_PRIVATE)
        editor = preferences.edit()
    }
    fun savePref(prefName: String , value:String)  =
            editor.putString(prefName , value).commit()

    fun deletePref(prefName: String) =
            editor.remove(prefName).commit()

    fun getPref(prefName: String): String? =
            preferences.getString(prefName , null)

}