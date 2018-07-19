package pl.thor.app.dependencies

import javax.inject.Inject

import pl.thor.app.App
import pl.thor.app.api.RestClient
import pl.thor.app.prefs.Prefs

class DependencyPack {
    @Inject lateinit var prefs: Prefs
    @Inject lateinit var restClient: RestClient

    init {
        App.component.inject(this)
    }
}
