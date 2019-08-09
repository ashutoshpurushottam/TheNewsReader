package com.eigendaksh.thenewsreader.ui.views


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi

import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.utils.AppConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_news_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsDetailFragment : Fragment() {

    private val TAG by lazy { NewsDetailFragment::class.java.simpleName }

    private var webUrl: String? = ""
    private var title: String? = ""
    private var isRead: Boolean = false
    private var isPopular: Boolean = false
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            webUrl = it.getString(AppConstants.URL_KEY)
            title = it.getString(AppConstants.TITLE_KEY)
            isRead = it.getBoolean(AppConstants.IS_READ_KEY)
            isPopular = it.getBoolean(AppConstants.IS_POPULAR_KEY)
        }

        Log.d(TAG, "webUrl: $webUrl")
        Log.d(TAG, "title: $title")
        Log.d(TAG, "isRead: $isRead")
        Log.d(TAG, "isPopular: $isPopular")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)?.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()


        webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(webUrl)
                return false
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                progressBar?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar?.visibility = View.GONE
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(webUrl)
    }

}


