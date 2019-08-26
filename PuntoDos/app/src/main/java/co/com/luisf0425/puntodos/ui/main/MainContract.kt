package co.com.luisf0425.puntodos.ui.main

import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showFragmentDetails(post: Post)
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun optionDrawFragmentDetails(post: Post)
        fun loadListFragment()
    }
}