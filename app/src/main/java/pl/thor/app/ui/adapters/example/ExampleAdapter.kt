package pl.thor.app.ui.adapters.example

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

class ExampleAdapter(internal var listener: (String) -> Unit) : FlexibleAdapter<AbstractFlexibleItem<*>>(null) {
    fun displayData(list: List<String>?) {
        updateDataSet(createList(list ?: listOf(), listener))
    }

    private fun createList(orderList: List<String>, listener: (String) -> Unit): List<AbstractFlexibleItem<*>> {
        val items = mutableListOf<ExampleItem>()
        for (order in orderList) {
            items.add(ExampleItem(order, listener))
        }
        return items
    }
}