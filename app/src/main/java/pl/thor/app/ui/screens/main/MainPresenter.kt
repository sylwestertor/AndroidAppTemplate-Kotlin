package pl.thor.app.ui.screens.main

import pl.thor.app.api.RestClient
import pl.thor.app.dependencies.DependencyPack
import pl.thor.app.prefs.Prefs
import pl.thor.app.ui.screens.base.BasePresenter

class MainPresenter(view: MainActivityInterface, pack: DependencyPack) : BasePresenter<MainActivityInterface>(view, pack) {
    internal var restClient: RestClient = pack.restClient
    internal var prefs: Prefs = pack.prefs

    override fun init() {
        view.displayDummyList(listOf("Example", "Flexible", "Adapter", "List"))
    }
}
