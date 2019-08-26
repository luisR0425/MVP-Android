package co.com.luisf0425.puntodos.di.module

import android.app.Application
import co.com.luisf0425.puntodos.BaseAplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseAplication: BaseAplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseAplication
    }
}