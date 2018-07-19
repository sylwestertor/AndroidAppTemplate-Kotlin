package pl.thor.app.api.retrofit

import okhttp3.Interceptor
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import pl.thor.app.prefs.Prefs

object RetrofitClient {
    fun createRetrofit(hostUrl: String, timeout: Long, prefs: Prefs): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor(prefs))
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(hostUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    class HeaderInterceptor(internal var prefs: Prefs) : okhttp3.Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request: okhttp3.Request = chain.request()

            if (prefs.token == null)
                return chain.proceed(request)

            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + prefs.token?.accessToken)
                    .build()

            return chain.proceed(request)
        }
    }

    fun <T> send(call: retrofit2.Call<T>, callback: ApiCallback<T>): ApiCall<T> {
        val apiCall = RetrofitClient.wrapCall(call)
        apiCall.send(callback)
        return apiCall
    }

    private fun <T> wrapCall(call: retrofit2.Call<T>): ApiCall<T> {
        return object : ApiCall<T> {
            override fun send(callback: ApiCallback<T>) {
                call.enqueue(wrapCallback(callback))
            }

            override fun cancel() {
                call.cancel()
            }

            override val isCanceled: Boolean = call.isCanceled;
        }
    }

    private fun <T> wrapCallback(callback: ApiCallback<T>): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onFailure(ApiError(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t is TimeoutException) {
                    callback.onFailure(ApiError(ApiError.Type.TIMEOUT))
                } else {
                    callback.onFailure(ApiError(ApiError.Type.OTHER, message = t.javaClass.name + " " + t.message))
                }
            }
        }
    }

}
