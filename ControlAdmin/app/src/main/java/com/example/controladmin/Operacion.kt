package com.example.controladmin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Operacion {

    fun onCall(context: Context,telf:String) {
        val telefono = "tel:"+telf
        Toast.makeText(context, ("Llamando al " + telf), Toast.LENGTH_SHORT).show()
        val numero: Uri = Uri.parse(telefono)
        var intent = Intent(Intent.ACTION_DIAL, numero)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}