package pl.alab.online.ui.screens.base

import pl.alab.online.api.retrofit.ApiError

interface BaseActivityInterface {
    fun showMessage(message: String)

    fun showError(message: String)

    fun showError(apiError: ApiError)
}
