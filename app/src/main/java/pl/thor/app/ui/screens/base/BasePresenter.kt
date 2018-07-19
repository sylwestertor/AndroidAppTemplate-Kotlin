package pl.thor.app.ui.screens.base

import pl.thor.app.dependencies.DependencyPack
import pl.thor.app.prefs.Prefs

abstract class BasePresenter<T : BaseActivityInterface>(protected var view: T, pack: DependencyPack) {
    abstract fun init()
}
