package co.com.luisf0425.puntodos.di.module

import co.com.luisf0425.puntodos.api.ApiServiceInterface
import co.com.luisf0425.puntodos.ui.details.DetailsContract
import co.com.luisf0425.puntodos.ui.details.DetailsPresenter
import co.com.luisf0425.puntodos.ui.list.ListContract
import co.com.luisf0425.puntodos.ui.list.ListPresenter
import co.com.luisf0425.puntodos.ui.login.LoginContract
import co.com.luisf0425.puntodos.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): DetailsContract.Presenter {
        return DetailsPresenter() //Return Presenter for Fragments
    }

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter() //Return presenter of ListContract for Fragments
    }

    @Provides
    fun provideLoginPresenter(): LoginContract.Presenter{
        return LoginPresenter() //Return presenter of LoginPresenter for Fragments
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create() //Return retrofitApi for presenter of fragments
    }
}