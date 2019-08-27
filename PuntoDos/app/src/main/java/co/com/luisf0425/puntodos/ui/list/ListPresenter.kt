package co.com.luisf0425.puntodos.ui.list

import co.com.luisf0425.puntodos.api.ApiServiceInterface
import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.model.PostDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListPresenter: ListEvents.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ListEvents.View
    private lateinit var subscription: Disposable

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListEvents.View) {
        this.view = view
    }

    override fun loadData(postDao: PostDao) {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap {
                dbPostList ->
                    if (dbPostList.isEmpty()){
                        api.getPostList().concatMap {
                                apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                                Observable.just(apiPostList)
                        }
                    }else{
                        Observable.just(dbPostList)
                    }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    list: List<Post>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })
        subscriptions.add(subscription)
        /*var subscribe = api.getPostList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Post>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)*/
    }

    override fun deleteItem(item: Post) {
        //api.deleteUser(item.id)
    }
}