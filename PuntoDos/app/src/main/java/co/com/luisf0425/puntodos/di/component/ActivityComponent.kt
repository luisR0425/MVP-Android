package co.com.luisf0425.puntodos.di.component

import co.com.luisf0425.puntodos.di.module.ActivityModule
import co.com.luisf0425.puntodos.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity) //Inject ActivityModule to MainActivity

}