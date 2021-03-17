package com.example.nikolaiturev.weather.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.nikolaiturev.weather.R

abstract class BaseActivity : AppCompatActivity() {
    protected abstract var layoutId: Int

    protected abstract fun iniView()

    private lateinit var progressDialog: Dialog
    open val viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        progressDialog()
        bindViewModel()
        iniView()
    }

    private fun bindViewModel() {

        viewModel?.isInProgress?.observe(this, { isInProgress ->
            if (isInProgress) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })
    }

    private fun progressDialog() {
        progressDialog = Dialog(this)
        val inflate = LayoutInflater.from(this).inflate(R.layout.layout_progress, null)
        progressDialog.setContentView(inflate)
        progressDialog.setCancelable(false)
        progressDialog.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }
}
