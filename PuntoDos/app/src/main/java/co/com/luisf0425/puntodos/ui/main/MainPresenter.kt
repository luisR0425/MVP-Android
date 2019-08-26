package co.com.luisf0425.puntodos.ui.main

import co.com.luisf0425.puntodos.model.Post
import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showListFragment() // as default
    }

    override fun optionDrawFragmentDetails(post: Post) {
        view.showFragmentDetails(post)
    }

    override fun loadListFragment() {
        view.showListFragment()
    }
}