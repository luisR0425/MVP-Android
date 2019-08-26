package co.com.luisf0425.puntodos.di.module

import android.app.Activity
import co.com.luisf0425.puntodos.ui.main.MainContract
import co.com.luisf0425.puntodos.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}