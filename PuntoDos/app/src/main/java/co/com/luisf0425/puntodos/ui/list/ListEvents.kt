package co.com.luisf0425.puntodos.ui.list

import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.ui.base.BaseContract

class ListEvents {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Post>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun deleteItem(item: Post)
    }
}