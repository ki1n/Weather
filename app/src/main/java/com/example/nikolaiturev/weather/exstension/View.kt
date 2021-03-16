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

private const val DEBOUNCE: Long = 300L
private var isClickEnabled: Boolean = true
private val enable: () -> Unit = { isClickEnabled = true }

/**
 * Клик-листенер, предотвращающий многократные быстрые клики.
 * После удачного клика, другие клики не срабатывают в течение 300мс
 */
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    this.setOnClickListener { v ->
        if (isClickEnabled) {
            isClickEnabled = false
            v.postDelayed(enable, DEBOUNCE)
            action.invoke()
        }
    }
}




