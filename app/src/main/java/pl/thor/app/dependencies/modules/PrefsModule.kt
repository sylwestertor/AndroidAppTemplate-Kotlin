package pl.thor.app.dependencies.modules

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import pl.thor.app.App
import pl.thor.app.prefs.Prefs
import pl.thor.app.prefs.PrefsImpl

@Module
class PrefsModule {
    @Provides
    @Singleton
    internal fun providePrefs(sharedPreferences: SharedPreferences, gson: Gson): Prefs {
        return PrefsImpl(sharedPreferences, gson)
    }

    @Provides
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(App.context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    internal fun provideContext(): Context {
        return App.context
    }
}
