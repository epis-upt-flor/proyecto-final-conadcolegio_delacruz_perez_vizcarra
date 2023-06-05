package com.example.controladmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.controladmin.navigation.Destination
import com.example.controladmin.presentation.personal_detail.PersonalDetailScreen
import com.example.controladmin.presentation.personal_detail.PersonalDetailViewModel
import com.example.controladmin.presentation.personal_list.PersonalListScreen
import com.example.controladmin.presentation.personal_list.components.PersonalListViewModel
import com.example.controladmin.presentation.profile.ProfileScreen
import com.example.controladmin.presentation.sign_in.GoogleAuthUiClient
import com.example.controladmin.presentation.sign_in.SignInScreen
import com.example.controladmin.presentation.sign_in.SignInViewModel
import com.example.controladmin.ui.theme.ControlAdminTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlAdminTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable(route = "sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("profile")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("profile")
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }

                        composable(route = "profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                },
                                checkPersonal = {
                                    navController.navigate(route = Destination.PersonalList.route)
                                }
                            )
                        }

                        addPersonalList(navController)

                        addPersonalDetail()

                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.addPersonalList(
    navController: NavController
){
    composable(
        route = Destination.PersonalList.route
    ){
        val viewModel: PersonalListViewModel = hiltViewModel()
        val state = viewModel.state.value
        val isRefreshing = viewModel.isRefreshing.collectAsState()

        PersonalListScreen(
            state = state,
            navigateToPersonalDetail = {
                navController.navigate(Destination.PersonalDetail.route)
            },
            isRefreshing = isRefreshing.value,
            refreshData = viewModel::getPersonalList
        )
    }
}

fun NavGraphBuilder.addPersonalDetail() {
    composable(
        route = Destination.PersonalDetail.route
    ){
        val viewModel: PersonalDetailViewModel = hiltViewModel()
        val state = viewModel.state.value

        PersonalDetailScreen(
            state = state,
            addNewPersonal = viewModel::addNewPersonal
        )
    }
}