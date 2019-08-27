package co.com.luisf0425.puntodos.ui.details

import co.com.luisf0425.puntodos.ui.base.BaseContract

class DetailsContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadMessageSuccess(message: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadMessage()
    }
}