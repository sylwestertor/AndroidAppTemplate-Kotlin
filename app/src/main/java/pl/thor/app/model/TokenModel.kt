package pl.thor.app.model

import com.google.gson.annotations.SerializedName

data class Token(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("refresh_token") val refreshToken: String,
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("expires_in") val expiresIn: Int,
        @SerializedName("scope") val scope: String
)