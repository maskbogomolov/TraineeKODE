package com.example.appkode.presentation

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appkode.util.Const.ANALYTICS_DEP
import com.example.appkode.util.Const.ANDROID_DEP
import com.example.appkode.util.Const.BACKEND_DEP
import com.example.appkode.util.Const.BACK_DEP
import com.example.appkode.util.Const.DESIGN_DEP
import com.example.appkode.util.Const.FRONTEND_DEP
import com.example.appkode.util.Const.HR_DEP
import com.example.appkode.util.Const.IOS_DEP
import com.example.appkode.util.Const.KEY
import com.example.appkode.util.Const.MANAGEMENT_DEP
import com.example.appkode.util.Const.PR_DEP
import com.example.appkode.util.Const.QA_DEP
import com.example.appkode.util.Const.SUPPORT_DEP

const val PAGE_INDEX_0 = 0
const val PAGE_INDEX_1 = 1
const val PAGE_INDEX_2 = 2
const val PAGE_INDEX_3 = 3
const val PAGE_INDEX_4 = 4
const val PAGE_INDEX_5 = 5
const val PAGE_INDEX_6 = 6
const val PAGE_INDEX_7 = 7
const val PAGE_INDEX_8 = 8
const val PAGE_INDEX_9 = 9
const val PAGE_INDEX_10 = 10
const val PAGE_INDEX_11 = 11
const val PAGE_INDEX_12 = 12

class PagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        PAGE_INDEX_0 to { UsersListFragment() },
        PAGE_INDEX_1 to { UsersListFragment() },
        PAGE_INDEX_2 to { UsersListFragment() },
        PAGE_INDEX_3 to { UsersListFragment() },
        PAGE_INDEX_4 to { UsersListFragment() },
        PAGE_INDEX_5 to { UsersListFragment() },
        PAGE_INDEX_6 to { UsersListFragment() },
        PAGE_INDEX_7 to { UsersListFragment() },
        PAGE_INDEX_8 to { UsersListFragment() },
        PAGE_INDEX_9 to { UsersListFragment() },
        PAGE_INDEX_10 to { UsersListFragment() },
        PAGE_INDEX_11 to { UsersListFragment() },
        PAGE_INDEX_12 to { UsersListFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreator.size
    }

    //Далее много отделов, наверное это не очень плохой код, понятно что происходит
    override fun createFragment(position: Int): Fragment {
        val fragment = UsersListFragment()
        return when (position) {
            PAGE_INDEX_0 -> {
                fragment
            }
            PAGE_INDEX_1 -> {
                fragment.arguments = Bundle().apply { putString(KEY, ANDROID_DEP) }
                fragment
            }
            PAGE_INDEX_2 -> {
                fragment.arguments = Bundle().apply { putString(KEY, IOS_DEP) }
                fragment
            }
            PAGE_INDEX_3 -> {
                fragment.arguments = Bundle().apply { putString(KEY, DESIGN_DEP) }
                fragment
            }
            PAGE_INDEX_4 -> {
                fragment.arguments = Bundle().apply { putString(KEY, MANAGEMENT_DEP) }
                fragment
            }
            PAGE_INDEX_5 -> {
                fragment.arguments = Bundle().apply { putString(KEY, QA_DEP) }
                fragment
            }
            PAGE_INDEX_6 -> {
                fragment.arguments = Bundle().apply { putString(KEY, BACK_DEP) }
                fragment
            }
            PAGE_INDEX_7 -> {
                fragment.arguments = Bundle().apply { putString(KEY, FRONTEND_DEP) }
                fragment
            }
            PAGE_INDEX_8 -> {
                fragment.arguments = Bundle().apply { putString(KEY, HR_DEP) }
                fragment
            }
            PAGE_INDEX_9 -> {
                fragment.arguments = Bundle().apply { putString(KEY, PR_DEP) }
                fragment
            }
            PAGE_INDEX_10 -> {
                fragment.arguments = Bundle().apply { putString(KEY, BACKEND_DEP) }
                fragment
            }
            PAGE_INDEX_11 -> {
                fragment.arguments = Bundle().apply { putString(KEY, SUPPORT_DEP) }
                fragment
            }
            PAGE_INDEX_12 -> {
                fragment.arguments = Bundle().apply { putString(KEY, ANALYTICS_DEP) }
                fragment
            }
            else -> throw IndexOutOfBoundsException()
        }

    }
}