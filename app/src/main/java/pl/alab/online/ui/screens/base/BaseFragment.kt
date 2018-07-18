package pl.alab.online.ui.screens.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife
import pl.alab.online.api.retrofit.ApiError
import pl.alab.online.dependencies.DependencyPack

abstract class BaseFragment<T : BasePresenter<*>> : Fragment(), BaseActivityInterface {
    protected lateinit var activity: BaseActivity<*>
    protected lateinit var presenter: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity = getActivity() as BaseActivity<*>

        val myFragmentView = inflater.inflate(createLayout(), container, false)
        ButterKnife.bind(this, myFragmentView)

        val pack = DependencyPack()
        presenter = createPresenter(pack)

        return myFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun createLayout(): Int

    protected abstract fun createPresenter(pack: DependencyPack): T

    override fun showMessage(message: String) {
        activity.showMessage(message)
    }

    override fun showError(message: String) {
        activity.showError(message)
    }

    override fun showError(apiError: ApiError) {
        activity.showError(apiError)
    }
}
