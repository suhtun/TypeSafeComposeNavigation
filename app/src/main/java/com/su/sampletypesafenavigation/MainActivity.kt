package com.su.sampletypesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.su.sampletypesafenavigation.ui.theme.SampleTypeSafeNavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTypeSafeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Setting) {
                        composable<Setting> {
                            SettingScreen(
                                modifier = Modifier.padding(innerPadding),
                                onClick = {
                                    navController.navigate(Profile(1, "Su"))
                                })

                        }
                        composable<Profile> { backStackEntry ->
                            val profile: Profile = backStackEntry.toRoute()
                            ProfileScreen(profile.id, profile.name)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data object Setting

@Composable
fun SettingScreen(
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onClick() }) {
            Text(text = "Go To Profile")
        }
    }
}

@Serializable
data class Profile(val id: Int, val name: String)

@Composable
fun ProfileScreen(id: Int, name: String) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name)
    }
}
