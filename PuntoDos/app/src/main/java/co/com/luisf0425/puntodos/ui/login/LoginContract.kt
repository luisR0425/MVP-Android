package co.com.luisf0425.puntodos.ui.login

import co.com.luisf0425.puntodos.ui.base.BaseContract

class LoginContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun eventLogin(username: String?)
    }
}