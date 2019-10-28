package co.com.luisf0425.puntodos.ui.list

import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.model.PostDao
import co.com.luisf0425.puntodos.ui.base.BaseContract

class ListContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Post>)
        fun itemRemoveSuccess(post: Post)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(postDao: PostDao)
        fun deleteItem(postDao: PostDao, item: Post)
        fun updateItem(postDao: PostDao, item: Post)
    }
}