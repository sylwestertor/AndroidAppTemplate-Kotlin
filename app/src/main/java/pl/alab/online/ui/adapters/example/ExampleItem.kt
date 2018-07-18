package pl.alab.online.ui.adapters.example

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import pl.alab.online.R

class ExampleItem(internal var string: String, internal var listener: (String) -> Unit) : AbstractFlexibleItem<ExampleItem.ViewHolder>() {
    override fun getLayoutRes(): Int = R.layout.item_example
    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<*>>): ViewHolder = ViewHolder(view, adapter)

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?, holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        holder?.tvExampleItem?.text = string
        holder?.tvExampleItem?.setOnClickListener {
            val item = adapter?.getItem(position) as ExampleItem?
            item?.listener?.invoke(string)
        }
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        var tvExampleItem = view.findViewById(R.id.tvExampleItem) as TextView?
    }

    override fun equals(o: Any?): Boolean = o == String
}
