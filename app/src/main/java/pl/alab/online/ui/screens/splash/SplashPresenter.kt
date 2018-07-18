package pl.alab.online.ui.screens.splash

import android.os.Handler
import pl.alab.online.api.RestClient
import pl.alab.online.api.retrofit.ApiCallback
import pl.alab.online.api.retrofit.ApiError
import pl.alab.online.dependencies.DependencyPack
import pl.alab.online.prefs.Prefs
import pl.alab.online.ui.screens.base.BasePresenter

class SplashPresenter(view: SplashActivityInterface, pack: DependencyPack) : BasePresenter<SplashActivityInterface>(view, pack) {
    internal var prefs: Prefs = pack.prefs

    override fun init() {
        view.navigateToMainActivity()
    }
}
