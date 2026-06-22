package com.example.kmp_2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kmp_2.ui.screen.MainScreen
import com.example.kmp_2.ui.screen.ForegroundScreen
import com.example.kmp_2.ui.screen.BackgroundScreen
import com.example.kmp_2.ui.screen.RenderingScreen

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainNavHost()
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    val screens = listOf(
        NavItem("main",       "Main",       Icons.Default.Home),
        NavItem("foreground", "Foreground", Icons.Default.LocationOn),
        NavItem("background", "Background", Icons.Default.Settings),
        NavItem("rendering",  "Rendering",  Icons.Default.Star),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStack by navController.currentBackStackEntryAsState()
                val current = backStack?.destination?.route

                screens.forEach { item ->
                    NavigationBarItem(
                        selected = current == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main")       { MainScreen() }
            composable("foreground") { ForegroundScreen() }
            composable("background") { BackgroundScreen() }
            composable("rendering")  { RenderingScreen() }
        }
    }
}