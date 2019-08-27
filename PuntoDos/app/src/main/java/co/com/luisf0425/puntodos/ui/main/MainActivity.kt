package co.com.luisf0425.puntodos.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.di.component.DaggerActivityComponent
import co.com.luisf0425.puntodos.di.module.ActivityModule
import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.model.database.AppDatabase
import co.com.luisf0425.puntodos.ui.details.DetailsFragment
import co.com.luisf0425.puntodos.ui.list.ListFragment
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var postDatabase: AppDatabase

    lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }

    override fun showFragmentDetails(post: Post) {
        if (supportFragmentManager.findFragmentByTag(DetailsFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(AnimType.FADE.getAnimPair().first, AnimType.FADE.getAnimPair().second)
                .replace(R.id.frame, DetailsFragment(post).newInstance(post), DetailsFragment.TAG)
                .commit()
        }
    }

    override fun showListFragment() {
        listFragment = ListFragment().newInstance()
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .replace(R.id.frame, listFragment, ListFragment.TAG)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.nav_item_delete -> {
                listFragment.listAdapter.removeAll()
                return true
            }
            R.id.nav_item_reload -> {
                presenter.loadListFragment()
                return true
            }
            else -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(DetailsFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    enum class AnimType {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            return when(this) {
                SLIDE -> Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> Pair(R.anim.fade_in, R.anim.fade_out)
            }

            return Pair(R.anim.slide_left, R.anim.slide_right)
        }
    }
}