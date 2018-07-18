package pl.alab.online.api.retrofit

import com.google.gson.Gson
import pl.alab.online.model.ErrorModel
import retrofit2.Response

class ApiError(type: Type, statusCode: Int? = null, message: String? = null) {
    val type: Type
    var statusCode: Int?
    var errorCodeInternal: Int?
    var message: String?

    enum class Type {
        NETWORK, TIMEOUT, OTHER
    }

    init {
        this.type = type
        this.statusCode = statusCode
        this.errorCodeInternal = null
        this.message = message
    }

    constructor(response: Response<*>) : this(Type.NETWORK) {
        this.statusCode = response.code()
        try {
            val errorModel: ErrorModel = Gson().fromJson(response.errorBody()?.string(), ErrorModel::class.java)
            this.message = errorModel.message
            this.errorCodeInternal = errorModel.errorCode
        } catch (e: Exception) {
            this.message = response.message() + "\n" + response.errorBody()?.string()
        }
    }
}
