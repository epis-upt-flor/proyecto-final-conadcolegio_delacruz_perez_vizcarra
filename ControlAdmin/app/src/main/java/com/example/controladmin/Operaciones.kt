package com.example.controladmin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Operaciones {
    fun llamarContacto(context: Context, telf:String) {
        val telefono = "tel:"+telf
        Toast.makeText(context, ("Llamando al " + telf),Toast.LENGTH_SHORT).show()

        val numero: Uri = Uri.parse(telefono)
        var intent = Intent(Intent.ACTION_DIAL, numero)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun onWhatsApp(context: Context,telf:String){
        val intent = Intent(Intent.ACTION_VIEW)
        val whts: Uri = Uri.parse("whatsapp://send?phone="+telf)
        intent.setData(whts)
        context.startActivity(intent)
    }

}