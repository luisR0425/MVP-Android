package co.com.luisf0425.puntodos.di.component

import co.com.luisf0425.puntodos.BaseAplication
import co.com.luisf0425.puntodos.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseAplication) //Inject ApplicationModule to BaseAplication

}