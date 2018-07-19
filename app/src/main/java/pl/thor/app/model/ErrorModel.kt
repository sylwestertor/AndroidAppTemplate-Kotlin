package pl.thor.app.model

import com.google.gson.annotations.SerializedName

data class ErrorModel(
        @SerializedName("code") val type: String,
        @SerializedName("message") val message: String,
        @SerializedName("error_code") val errorCode: Int
)