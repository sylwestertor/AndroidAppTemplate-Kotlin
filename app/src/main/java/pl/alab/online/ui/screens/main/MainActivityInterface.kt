package pl.alab.online.ui.screens.main

import pl.alab.online.ui.screens.base.BaseActivityInterface

interface MainActivityInterface : BaseActivityInterface {
    fun displayDummyList(list: List<String>?)
}
