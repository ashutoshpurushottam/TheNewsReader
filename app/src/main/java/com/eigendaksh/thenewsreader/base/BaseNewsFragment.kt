package com.eigendaksh.thenewsreader.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemAdapter
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemListener
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseNewsFragment : Fragment(), NewsItemListener {

    private lateinit var recyclerView: RecyclerView
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_bar)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)?.visibility = View.VISIBLE
        val adapter = NewsItemAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter
    }

    fun updateItems(itemList: List<NewsItem>) {
        val adapter = recyclerView.adapter as NewsItemAdapter
        adapter.addItems(itemList)
    }


    fun showToast(msg: String?) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun hideProgress() {
       progressBar?.visibility = View.GONE
    }


    fun getLayoutId() = R.layout.fragment_stories

    abstract fun getNewsItemList(): List<NewsItem>?


}