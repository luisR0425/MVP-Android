package co.com.luisf0425.puntodos.di.module

import co.com.luisf0425.puntodos.api.ApiServiceInterface
import co.com.luisf0425.puntodos.ui.details.DetailsContract
import co.com.luisf0425.puntodos.ui.details.DetailsPresenter
import co.com.luisf0425.puntodos.ui.list.ListEvents
import co.com.luisf0425.puntodos.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): DetailsContract.Presenter {
        return DetailsPresenter()
    }

    @Provides
    fun provideListPresenter(): ListEvents.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}