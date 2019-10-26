package co.com.luisf0425.puntodos.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import co.com.luisf0425.puntodos.di.component.DaggerFragmentComponent
import co.com.luisf0425.puntodos.di.module.FragmentModule
import javax.inject.Inject

class LoginFragment: Fragment(), LoginContract.View {
    override fun showProgress(show: Boolean) {
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val loginComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        loginComponent.inject(this)
    }
}