package com.kotlin.demo_week2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kotlin.demo_week2.R
import com.kotlin.demo_week2.adapter.TablayoutAdapter
import com.kotlin.demo_week2.fragment.AccountFragment
import com.kotlin.demo_week2.fragment.InformationFragment
import com.kotlin.demo_week2.mvp.model.Messages
import com.kotlin.demo_week2.mvp.presenter.PresenterAccount
import com.kotlin.demo_week2.mvp.view.IMainView
import com.kotlin.demo_week2.unit.SourceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), IMainView, CoroutineScope {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    lateinit var presenter: PresenterAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById<TabLayout>(R.id.tabMode)
        viewPager = findViewById<ViewPager>(R.id.vPage)

        tabLayout!!.addTab(tabLayout!!.newTab().setText(getString(R.string.title_account)))
        tabLayout!!.addTab(tabLayout!!.newTab().setText(getString(R.string.information)))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TablayoutAdapter(supportFragmentManager)
        adapter.addFragment(AccountFragment(), getString(R.string.title_account))
        adapter.addFragment(InformationFragment(), getString(R.string.information))

        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem == tab?.position
                Log.d("TabLayout", "position ${tab?.position}")
            }
        })

        presenter = PresenterAccount(Dispatchers.IO, coroutineContext, SourceData, this)
        presenter.fetchAccount()
    }

    override fun showView(infoData: List<Messages>?) {
        Log.d("DoanPhu", "Data: ${infoData?.size}")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}
