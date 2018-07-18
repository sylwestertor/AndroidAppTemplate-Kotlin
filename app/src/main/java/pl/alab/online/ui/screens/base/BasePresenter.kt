package pl.alab.online.ui.screens.base

import pl.alab.online.dependencies.DependencyPack
import pl.alab.online.prefs.Prefs

abstract class BasePresenter<T : BaseActivityInterface>(protected var view: T, pack: DependencyPack) {
    abstract fun init()
}
