package magazine.luiza.pocwebview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_title)
        setUpAd(
            webViewId = R.id.ad_adaptative,
            loadViewId = R.id.load_adaptative,
            errorViewId = R.id.error_adaptative,
            iframe = R.string.iframe_adaptative
        )
        setUpAd(
            webViewId = R.id.ad_320_100,
            loadViewId = R.id.load_320_100,
            errorViewId = R.id.error_320_100,
            iframe = R.string.iframe_320_100
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpAd(
        @IdRes webViewId: Int,
        @IdRes loadViewId: Int,
        @IdRes errorViewId: Int,
        @StringRes iframe: Int
    ) {
        val webView = findViewById<WebView>(webViewId)
        val load = findViewById<ProgressBar>(loadViewId)
        val error = findViewById<TextView>(errorViewId)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {

            private var hasError = false

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                hasError = false
                webView.visibility = View.INVISIBLE
                load.visibility = View.VISIBLE
                error.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.visibility = if (hasError) View.INVISIBLE else View.VISIBLE
                load.visibility = View.GONE
                error.visibility = if (hasError) View.VISIBLE else View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                hasError = true
            }
        }
        webView.loadData(getString(iframe), "text/html", "utf-8")
    }

}
