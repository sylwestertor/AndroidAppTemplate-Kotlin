package pl.alab.online.ui.screens.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import butterknife.ButterKnife
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import pl.alab.online.api.retrofit.ApiError
import pl.alab.online.dependencies.DependencyPack

abstract class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), BaseActivityInterface {

    protected lateinit var activity: Activity
    protected lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this

        val layout = createLayout()
        if (layout != null) {
            setContentView(layout)
            ButterKnife.bind(this)
        }

        val pack = DependencyPack()
        presenter = createPresenter(pack)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun createLayout(): Int

    protected abstract fun createPresenter(pack: DependencyPack): T

    override fun showMessage(message: String) {
        alert(message) { positiveButton("OK", { dialog -> dialog.dismiss() }) }.show()
    }

    override fun showError(message: String) {
        alert(message) { positiveButton("OK", { dialog -> dialog.dismiss() }) }.show()
    }

    override fun showError(apiError: ApiError) {
        alert(apiError.message.toString()) {
            title = "ERROR " + apiError.errorCodeInternal?.toString()
            positiveButton("OK", { dialog -> dialog.dismiss() })
        }.show()
    }
}
