package pl.thor.app.api.retrofit

interface ApiCallback<T> {
    fun onSuccess(response: T?)

    fun onFailure(error: ApiError)
}
