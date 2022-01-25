package com.example.appkode.util

import android.content.Context
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.appkode.R

fun ImageView.selectTrueFilterIcon(context: Context){
    this.setImageDrawable((ContextCompat.getDrawable(context, R.drawable.selectedicon)))
}
fun ImageView.selectFalseFilterIcon(context: Context){
    this.setImageDrawable((ContextCompat.getDrawable(context, R.drawable.notselected)))
}
fun MenuItem.changeTrueItemColor(context: Context){
    this.icon.setTint(ContextCompat.getColor(context,R.color.activePrimary))
}
fun MenuItem.changeFalseItemColor(context: Context){
    this.icon.setTint(ContextCompat.getColor(context,R.color.textTetriary))
}