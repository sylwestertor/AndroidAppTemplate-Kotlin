package pl.alab.online.dependencies

import javax.inject.Singleton

import dagger.Component
import pl.alab.online.dependencies.modules.PrefsModule
import pl.alab.online.dependencies.modules.RestClientModule

@Singleton
@Component(modules = arrayOf(PrefsModule::class, RestClientModule::class))
interface AppComponent {
    fun inject(pack: DependencyPack)
}
