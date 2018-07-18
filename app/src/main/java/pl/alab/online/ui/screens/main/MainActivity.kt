package pl.alab.online.ui.screens.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

import pl.alab.online.R
import pl.alab.online.dependencies.DependencyPack
import pl.alab.online.ui.adapters.example.ExampleAdapter
import pl.alab.online.ui.dialogs.ExampleDialog
import pl.alab.online.ui.screens.base.BaseActivity

class MainActivity : BaseActivity<MainPresenter>(), MainActivityInterface {
    override fun createLayout() = R.layout.activity_main
    override fun createPresenter(pack: DependencyPack) = MainPresenter(this, pack)

    lateinit var dialog: ExampleDialog
    lateinit var adapter: ExampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ExampleAdapter({ string -> toast(string) })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        dialog = ExampleDialog(this) { string -> alert(string).show() }

        presenter.init()
    }

    override fun displayDummyList(list: List<String>?) {
        adapter.displayData(list)
    }

    @OnClick(R.id.fab)
    fun onFabClicked() {
        if(!dialog.isShowing)
            dialog.show()
    }
}
