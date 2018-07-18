package pl.alab.online.ui.screens.main

import pl.alab.online.api.RestClient
import pl.alab.online.dependencies.DependencyPack
import pl.alab.online.prefs.Prefs
import pl.alab.online.ui.screens.base.BasePresenter

class MainPresenter(view: MainActivityInterface, pack: DependencyPack) : BasePresenter<MainActivityInterface>(view, pack) {
    internal var restClient: RestClient = pack.restClient
    internal var prefs: Prefs = pack.prefs

    override fun init() {
        view.displayDummyList(listOf("Example", "Flexible", "Adapter", "List"))
    }
}
