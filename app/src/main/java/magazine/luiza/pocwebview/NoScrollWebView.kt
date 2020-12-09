package magazine.luiza.pocwebview

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

class NoScrollWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    override fun overScrollBy(
        deltaX: Int,
        deltaY: Int,
        scrollX: Int,
        scrollY: Int,
        scrollRangeX: Int,
        scrollRangeY: Int,
        maxOverScrollX: Int,
        maxOverScrollY: Int,
        isTouchEvent: Boolean
    ): Boolean = false

    override fun scrollTo(x: Int, y: Int) {
    }

    override fun computeScroll() {
    }

}
