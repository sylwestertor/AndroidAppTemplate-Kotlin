package pl.thor.app.api

import pl.thor.app.model.*
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("auth/token")
    fun token(
            @Field("client_id") client_id: String,
            @Field("username") username: String?,
            @Field("password") password: String?,
            @Field("refresh_token") refreshToken: String?,
            @Field("grant_type") grantType: String
    ): Call<Token>

}
