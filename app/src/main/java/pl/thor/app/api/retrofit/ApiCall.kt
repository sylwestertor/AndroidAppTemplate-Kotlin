package pl.thor.app.api.retrofit

interface ApiCall<T> {
    val isCanceled: Boolean
    fun send(callback: ApiCallback<T>)
    fun cancel()
}
