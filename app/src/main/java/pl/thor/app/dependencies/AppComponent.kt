package pl.thor.app.dependencies

import javax.inject.Singleton

import dagger.Component
import pl.thor.app.dependencies.modules.PrefsModule
import pl.thor.app.dependencies.modules.RestClientModule

@Singleton
@Component(modules = arrayOf(PrefsModule::class, RestClientModule::class))
interface AppComponent {
    fun inject(pack: DependencyPack)
}
