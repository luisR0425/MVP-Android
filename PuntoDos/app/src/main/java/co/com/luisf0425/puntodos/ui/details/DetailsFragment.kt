package co.com.luisf0425.puntodos.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.di.component.DaggerFragmentComponent
import co.com.luisf0425.puntodos.di.module.FragmentModule
import co.com.luisf0425.puntodos.model.Post
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment(val post: Post): Fragment(), DetailsContract.View {

    @Inject
    lateinit var presenter: DetailsContract.Presenter

    private lateinit var rootView: View

    fun newInstance(post: Post): DetailsFragment {
        return DetailsFragment(post)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_details, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun loadMessageSuccess(message: String) {
        item_title.text = post.title
        item_body.text = post.body
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }

    private fun initView() {
        presenter.loadMessage()
    }

    companion object {
        val TAG: String = "DetailsFragment"
    }
}