package pl.alab.online.api

import pl.alab.online.api.retrofit.ApiCall
import pl.alab.online.api.retrofit.ApiCallback
import pl.alab.online.model.*

interface RestClient {
    fun tokenPassword(username: String, password: String, callback: ApiCallback<Token>): ApiCall<Token>

    fun tokenRefresh(refreshToken: String, callback: ApiCallback<Token>): ApiCall<Token>
}
