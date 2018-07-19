package pl.thor.app.ui.screens.base

import pl.thor.app.api.retrofit.ApiError

interface BaseActivityInterface {
    fun showMessage(message: String)

    fun showError(message: String)

    fun showError(apiError: ApiError)
}
