package co.com.luisf0425.puntodos.ui.login

import co.com.luisf0425.puntodos.api.ApiServiceInterface
import co.com.luisf0425.puntodos.model.User
import io.reactivex.disposables.Disposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class LoginPresenter: LoginContract.Presenter {
    private var subscriptions = CompositeDisposable()
    private lateinit var subscription: Disposable
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private lateinit var view: LoginContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun eventLogin(username: String?) {
        subscription = api.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list: List<User>? ->{}
                if(validateLogin(list, username)){
                    view.initListFragment()
                }
                view.showProgress(false)
                //view.loadDataSuccess(list!!)
            }, { error ->
                view.showProgress(false)
                //view.showErrorMessage(error.localizedMessage)
            })
        subscriptions.add(subscription)
    }

    fun validateLogin(list: List<User>?, username: String?): Boolean{
        var searched = false
        if (list != null) {
            for (user in list) {
                if(user.username == username){
                    searched = true
                    break
                }
            }
        }
        return searched
    }

}