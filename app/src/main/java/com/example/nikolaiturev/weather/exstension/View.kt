package com.example.nikolaiturev.weather.exstension

import android.view.View

fun View.show(display: Boolean = true) {
    visibility = display.check({ View.VISIBLE }, { View.GONE })
}

fun View.hide(display: Boolean = true) {
    show(!display)
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun <T> Boolean.check(isTrueAction: () -> T, isFalseAction: (() -> T)): T {
    return if (this) isTrueAction() else isFalseAction()
}

fun View.click(action: ((View) -> Unit)?) {
    setOnClickListener { action?.invoke(it) }
}




