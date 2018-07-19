package pl.thor.app.api

import retrofit2.Retrofit
import pl.thor.app.Config
import pl.thor.app.api.retrofit.ApiCall
import pl.thor.app.api.retrofit.ApiCallback
import pl.thor.app.api.retrofit.RetrofitClient
import pl.thor.app.model.*
import pl.thor.app.prefs.Prefs

class RestClientImpl(prefs: Prefs) : RestClient {
    protected var api: Api
    protected var retrofit: Retrofit

    init {
        this.retrofit = RetrofitClient.createRetrofit(Config.API_URL, Config.API_TIMEOUT, prefs)
        this.api = retrofit.create(Api::class.java)
    }

    override fun tokenPassword(username: String, password: String, callback: ApiCallback<Token>): ApiCall<Token> {
        return RetrofitClient.send(api.token(Config.CLIENT_ID, username, password, null, "password"), callback)
    }

    override fun tokenRefresh(refreshToken: String, callback: ApiCallback<Token>): ApiCall<Token> {
        return RetrofitClient.send(api.token(Config.CLIENT_ID, null, null, refreshToken, "refresh_token"), callback)
    }
}
