package pl.alab.online.dependencies.modules

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import pl.alab.online.api.RestClient
import pl.alab.online.api.RestClientImpl
import pl.alab.online.prefs.Prefs

@Module
class RestClientModule {
    @Provides
    @Singleton
    internal fun providesRestClient(prefs: Prefs): RestClient {
        return RestClientImpl(prefs)
    }
}
