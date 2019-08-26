package co.com.luisf0425.puntodos

import android.app.Application
import co.com.luisf0425.puntodos.di.component.ApplicationComponent
import co.com.luisf0425.puntodos.di.module.ApplicationModule
import co.com.luisf0425.puntodos.di.component.DaggerApplicationComponent

class BaseAplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        initialize()
    }

    private fun initialize() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: BaseAplication private set
    }
}