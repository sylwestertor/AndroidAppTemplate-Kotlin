package pl.thor.app.dependencies.modules

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import pl.thor.app.api.RestClient
import pl.thor.app.api.RestClientImpl
import pl.thor.app.prefs.Prefs

@Module
class RestClientModule {
    @Provides
    @Singleton
    internal fun providesRestClient(prefs: Prefs): RestClient {
        return RestClientImpl(prefs)
    }
}
