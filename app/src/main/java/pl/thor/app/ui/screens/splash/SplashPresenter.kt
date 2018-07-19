package pl.thor.app.ui.screens.splash

import android.os.Handler
import pl.thor.app.api.RestClient
import pl.thor.app.api.retrofit.ApiCallback
import pl.thor.app.api.retrofit.ApiError
import pl.thor.app.dependencies.DependencyPack
import pl.thor.app.prefs.Prefs
import pl.thor.app.ui.screens.base.BasePresenter

class SplashPresenter(view: SplashActivityInterface, pack: DependencyPack) : BasePresenter<SplashActivityInterface>(view, pack) {
    internal var prefs: Prefs = pack.prefs

    override fun init() {
        view.navigateToMainActivity()
    }
}
