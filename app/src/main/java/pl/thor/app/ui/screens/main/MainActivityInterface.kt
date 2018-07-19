package pl.thor.app.ui.screens.main

import pl.thor.app.ui.screens.base.BaseActivityInterface

interface MainActivityInterface : BaseActivityInterface {
    fun displayDummyList(list: List<String>?)
}
