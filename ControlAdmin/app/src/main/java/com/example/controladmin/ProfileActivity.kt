package com.example.controladmin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.controladmin.presentation.profile.ProfileScreen
import com.example.controladmin.ui.theme.ControlAdminTheme

class ProfileActivity : ComponentActivity(){
    companion object{
        private const val PERSONALADMIN_ID = "personaladmin_id"
        fun newIntent(context: Context, personalAdmin: PersonalAdmin) =
            Intent(context, ProfileActivity::class.java).apply{
                putExtra(PERSONALADMIN_ID,personalAdmin)
            }
    }

    private val personalAdmin: PersonalAdmin by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(PERSONALADMIN_ID,personalAdmin::class.java) as PersonalAdmin
        } else {
            intent?.getSerializableExtra(PERSONALADMIN_ID) as PersonalAdmin
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlAdminTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    /*ProfileScreen(
                        personalAdmin,
                        context = baseContext)*/
                }
            }
        }
    }
}