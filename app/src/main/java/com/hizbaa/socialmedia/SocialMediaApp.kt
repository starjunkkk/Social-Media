package com.hizbaa.socialmedia

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hizbaa.socialmedia.ui.HomeScreen
import com.hizbaa.socialmedia.ui.MessageScreen
import com.hizbaa.socialmedia.ui.ProfileScreen

enum class SocialMedia() {
    Home,
    Message,
    Profile
}

@Composable
fun SocialMediaBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: SocialMedia,
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    NavigationBar (
        modifier = modifier
    ){
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text("Home") },
            selected = currentRoute == SocialMedia.Home,
            onClick = { onHomeClick() },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = null
                )
            },
            label = { Text("Message") },
            selected = currentRoute == SocialMedia.Message,
            onClick = { onMessageClick() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text("Profile") },
            selected = currentRoute == SocialMedia.Profile,
            onClick = { onProfileClick() },
        )
    }
}

@Composable
fun SocialMediaApp(
    navController: NavHostController = rememberNavController(),
){
    Scaffold (
        bottomBar = {

            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = SocialMedia.valueOf(
                backStackEntry?.destination?.route ?: SocialMedia.Home.name
            )

            SocialMediaBottomBar(
                currentRoute = currentRoute,
                onHomeClick = {
                    Log.d("MyApp", "pencet home")
                    navController.navigate(SocialMedia.Home.name) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                              },
                onMessageClick = {
                    Log.d("MyApp", "pencet message")
                    navController.navigate(SocialMedia.Message.name) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                                 },
                onProfileClick = {
                    Log.d("MyApp", "pencet profile")
                    navController.navigate(SocialMedia.Profile.name) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SocialMedia.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable (route = SocialMedia.Home.name) {
                HomeScreen()
            }
            composable (route = SocialMedia.Message.name) {
                MessageScreen()
            }
            composable (route = SocialMedia.Profile.name) {
                ProfileScreen()
            }
        }
    }
}

