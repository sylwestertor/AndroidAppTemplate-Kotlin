package pl.alab.online

import android.app.Application
import android.content.Context

import pl.alab.online.dependencies.AppComponent
import pl.alab.online.dependencies.DaggerAppComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App : Application() {
    companion object {
        @JvmStatic lateinit var context: Context
        @JvmStatic val component: AppComponent = DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initCalligraphy()
    }

    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}
