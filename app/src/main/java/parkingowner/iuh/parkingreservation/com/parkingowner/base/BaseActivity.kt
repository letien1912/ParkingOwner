package parkingowner.iuh.parkingreservation.com.parkingowner.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import parkingowner.iuh.parkingreservation.com.parkingowner.R

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity(), BaseFragment.Callback {
    protected lateinit var presenter: P

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    protected fun configToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P


    override fun onFragmentAttached() { }

    override fun onFragmentDetached(tag: String) { }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}