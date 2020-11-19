//package com.app.movie.utils
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//
//class FragmentViewPagerAdapter(
//    fm: FragmentManager,
//    private val fragments: List<ViewPagerFragment>
//) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    override fun getItem(position: Int): Fragment {
//        val fragment = fragments[position].fragment
//        val arguments = Bundle()
//        arguments.putSerializable("profile", fragments[position].profile)
//        fragment.arguments = arguments
//        return fragment
//    }
//
//    override fun getCount(): Int {
//        return fragments.size
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return fragments[position].title
//    }
//
//
//}