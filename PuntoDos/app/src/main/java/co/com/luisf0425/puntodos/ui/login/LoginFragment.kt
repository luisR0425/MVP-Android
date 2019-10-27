package co.com.luisf0425.puntodos.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.databinding.FragmentListBindingImpl
import co.com.luisf0425.puntodos.databinding.FragmentLoginBindingImpl
import co.com.luisf0425.puntodos.di.component.DaggerFragmentComponent
import co.com.luisf0425.puntodos.di.module.FragmentModule
import co.com.luisf0425.puntodos.ui.main.MainActivity
import javax.inject.Inject

class LoginFragment: Fragment(), LoginContract.View {
    override fun showProgress(show: Boolean) {
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter
    private lateinit var binding: FragmentLoginBindingImpl

    fun newInstance(): LoginFragment{
        return LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()

        presenter.attach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    private fun injectDependency() {
        val loginComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        loginComponent.inject(this)
    }

    private fun initView() {
        val activity = activity as MainActivity
        //presenter.loadData(activity.postDatabase.postDao())
    }

    companion object {
        val TAG: String = "LoginFragment"
    }
}