package com.krachkovsky.viewpagerdbhomework.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krachkovsky.viewpagerdbhomework.databinding.ActivityMainBinding
import com.krachkovsky.viewpagerdbhomework.ui.fragment.AddToDatabaseFragment
import com.krachkovsky.viewpagerdbhomework.ui.fragment.DatabaseListFragment

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pagerAdapter = ScreenSlidePagerAdapter(this)

        with(binding) {

            pager.adapter = pagerAdapter

            btnNext.setOnClickListener {
                if (pager.currentItem < pagerAdapter.itemCount - 1) {
                    pager.currentItem++
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "This is the add new user page!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            btnPrevious.setOnClickListener {
                if (pager.currentItem > 0) {
                    pager.currentItem--
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "This is the user list page!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> DatabaseListFragment()
                1 -> AddToDatabaseFragment()
                else -> error("No fragment")
            }
        }
    }

    companion object {
        private const val NUM_PAGES = 2
    }
}