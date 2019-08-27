package co.com.luisf0425.puntodos.ui.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class DetailsPresenter: DetailsContract.Presenter {

    private lateinit var view: DetailsContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: DetailsContract.View) {
        this.view = view
    }

    override fun loadMessage() {
        // Wait for a moment
        Observable.just(true).delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.showProgress(false)
                view.loadMessageSuccess("Success")
            }
    }
}