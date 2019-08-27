package co.com.luisf0425.puntodos.di.module

import android.app.Activity
import androidx.room.Room
import co.com.luisf0425.puntodos.model.database.AppDatabase
import co.com.luisf0425.puntodos.ui.main.MainContract
import co.com.luisf0425.puntodos.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private var activity: Activity) {

    private lateinit var appDatabase: AppDatabase

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Singleton
    @Provides
    fun providesAppDatabase(): AppDatabase{
        appDatabase = Room.databaseBuilder(activity, AppDatabase::class.java, "post").build() //Instance of database as singleton
        return appDatabase
    }
}