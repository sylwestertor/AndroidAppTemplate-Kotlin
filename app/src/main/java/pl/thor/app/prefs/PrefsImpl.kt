package pl.thor.app.prefs

import android.content.SharedPreferences

import com.google.gson.Gson

import pl.thor.app.model.Token

class PrefsImpl(private val sharedPreferences: SharedPreferences, private val gson: Gson) : Prefs {

    companion object {
        private val KEY_TOKEN = "__token__"
    }

    override var token: Token?
        get() = gson.fromJson(sharedPreferences.getString(KEY_TOKEN, null), Token::class.java)
        set(token) = sharedPreferences.edit().putString(KEY_TOKEN, gson.toJson(token)).apply()
}
