package pl.alab.online.dependencies

import javax.inject.Inject

import pl.alab.online.App
import pl.alab.online.api.RestClient
import pl.alab.online.prefs.Prefs

class DependencyPack {
    @Inject lateinit var prefs: Prefs
    @Inject lateinit var restClient: RestClient

    init {
        App.component.inject(this)
    }
}
