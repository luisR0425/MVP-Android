package co.com.luisf0425.puntodos.di.component

import co.com.luisf0425.puntodos.di.module.FragmentModule
import co.com.luisf0425.puntodos.ui.details.DetailsFragment
import co.com.luisf0425.puntodos.ui.list.ListFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(detailsFragment: DetailsFragment) //Inject FragmentModule to DetailsFragment

    fun inject(listFragment: ListFragment) //Inject FragmentModule to ListFragment

}