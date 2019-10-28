package co.com.luisf0425.puntodos.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.databinding.FragmentLoginBindingImpl
import co.com.luisf0425.puntodos.di.component.DaggerFragmentComponent
import co.com.luisf0425.puntodos.di.module.FragmentModule
import co.com.luisf0425.puntodos.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: Fragment(), LoginContract.View {
    override fun showProgress(show: Boolean) {
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter
    private lateinit var binding: FragmentLoginBindingImpl

    private var btnLoginUsuario: Button? = null

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
        val view = binding.root
        initComponents(view)
        addEvents()
        return view
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

    private fun initComponents(view: View){
        btnLoginUsuario = view.findViewById(R.id.btnLoginUsuario)
    }

    private fun addEvents(){
        btnLoginUsuario?.setOnClickListener {
            val login = txtLoginUsuario.text.toString()
            //println("entraa")
            presenter.eventLogin(login)
        }
    }

    override fun initListFragment(){
        val activity = activity as MainActivity
        activity.presenter.loadListFragment()
    }

    companion object {
        val TAG: String = "LoginFragment"
    }
}