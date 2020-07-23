package com.lzp.dslui.dsl

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

fun <T : View> Activity.id(@IdRes id: Int): T {
    return this.findViewById(id)
}

fun View.id(@IdRes id: Int): View? {
    return this.findViewById(id)
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Activity.Column(
    orientation: Int,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0,
    block: Column.() -> Unit
): View {
    val linearLayout = LinearLayout(this)
    linearLayout.orientation = orientation

    val layoutParams = (linearLayout.layoutParams ?: ViewGroup.MarginLayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )) as ViewGroup.MarginLayoutParams

    layoutParams.leftMargin = leftMargin
    layoutParams.topMargin = topMargin
    layoutParams.rightMargin = rightMargin
    layoutParams.bottomMargin = bottomMargin
    linearLayout.layoutParams = layoutParams
    Column(linearLayout).block()
    return linearLayout
}

class Column(private val linearLayout: LinearLayout) {

    fun ImageView(
        id: Int,
        width: Int,
        height: Int,
        backgroundColor: Int,
        @DrawableRes src: Int = 0,
        onClickListener: View.OnClickListener? = null,
        block: (ImageView.() -> Unit)? = null
    ) {
        val imageView = ImageView(linearLayout.context)
        imageView.id = id
        imageView.setImageResource(src)
        imageView.setBackgroundColor(backgroundColor)
        onClickListener?.let {
            imageView.setOnClickListener(it)
        }
        block?.let { imageView.it() }

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.width = width
        layoutParams.height = height

        linearLayout.addView(imageView, layoutParams)
    }

    fun TextView(text: String, block: (TextView.() -> Unit)? = null) {
        val textView = TextView(linearLayout.context)
        textView.text = text
        block?.let { textView.it() }
        linearLayout.addView(textView)
    }

    fun Column(
        orientation: Int,
        leftMargin: Int = 0,
        topMargin: Int = 0,
        rightMargin: Int = 0,
        bottomMargin: Int = 0,
        layoutGravity: Int = Gravity.START,
        block: Column.() -> Unit
    ) {
        val linearLayout = LinearLayout(linearLayout.context)
        linearLayout.orientation = orientation

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.leftMargin = leftMargin
        layoutParams.topMargin = topMargin
        layoutParams.rightMargin = rightMargin
        layoutParams.bottomMargin = bottomMargin
        layoutParams.gravity = layoutGravity
        linearLayout.layoutParams = layoutParams

        Column(linearLayout).block()
        this.linearLayout.addView(linearLayout)
    }

}