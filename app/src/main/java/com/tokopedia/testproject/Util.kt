package com.tokopedia.testproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_problem.*


fun String.fromHtml(): Spanned {
    val htmlStr = replace("\n", "<br />")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlStr)
    }
}

fun toBitmap(context: Context, layoutResource: Int): Bitmap {
    return BitmapFactory.decodeResource(context.resources, layoutResource)
}

fun dpToPx(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun pxToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun WebView.loadFile(filePath: String) {
    loadUrl("file:///android_asset/$filePath");
}