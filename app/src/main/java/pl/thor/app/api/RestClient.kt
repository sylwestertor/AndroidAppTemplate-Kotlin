package pl.thor.app.api

import pl.thor.app.api.retrofit.ApiCall
import pl.thor.app.api.retrofit.ApiCallback
import pl.thor.app.model.*

interface RestClient {
    fun tokenPassword(username: String, password: String, callback: ApiCallback<Token>): ApiCall<Token>

    fun tokenRefresh(refreshToken: String, callback: ApiCallback<Token>): ApiCall<Token>
}
