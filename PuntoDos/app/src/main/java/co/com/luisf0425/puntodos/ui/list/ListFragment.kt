package co.com.luisf0425.puntodos.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.di.component.DaggerFragmentComponent
import co.com.luisf0425.puntodos.di.module.FragmentModule
import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.util.SwipeToDelete
import kotlinx.android.synthetic.main.fragment_details.progressBar
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject
import co.com.luisf0425.puntodos.databinding.FragmentListBindingImpl
import co.com.luisf0425.puntodos.ui.main.MainActivity

class ListFragment: Fragment(), ListContract.View, ListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: ListContract.Presenter
    private lateinit var binding: FragmentListBindingImpl
    lateinit var listAdapter: ListAdapter

    fun newInstance(): ListFragment {
        return ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
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

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Post>) {
        var adapter = activity?.let { ListAdapter(it, list.toMutableList(), this) }
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter
        listAdapter = adapter as ListAdapter

        val swipeHandler = object : SwipeToDelete(activity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun itemRemoveClick(post: Post) {
        val activity = activity as MainActivity
        presenter.deleteItem(activity.postDatabase.postDao(), post)
    }

    override fun itemDetail(post: Post) {
        val activity = activity as MainActivity
        activity.presenter.optionDrawFragmentDetails(post)
        post.isRead = true
        presenter.updateItem(activity.postDatabase.postDao(), post)
    }

    override fun itemRemoveSuccess(post: Post){
        val activity = activity as MainActivity
        activity.presenter.loadListFragment()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        val activity = activity as MainActivity
        presenter.loadData(activity.postDatabase.postDao())
    }

    companion object {
        val TAG: String = "ListFragment"
    }
}