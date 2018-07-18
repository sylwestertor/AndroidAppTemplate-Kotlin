package pl.alab.online.ui.screens.splash

import android.content.Intent
import android.os.Bundle

import pl.alab.online.R
import pl.alab.online.dependencies.DependencyPack
import pl.alab.online.ui.screens.base.BaseActivity
import pl.alab.online.ui.screens.main.MainActivity

class SplashActivity : BaseActivity<SplashPresenter>(), SplashActivityInterface {
    override fun createLayout() = R.layout.activity_splash
    override fun createPresenter(pack: DependencyPack) = SplashPresenter(this, pack)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.init()
    }

    override fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
