package com.example.nexcontacts.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nexcontacts.ui.contacts.ContactsScreen
import com.example.nexcontacts.ui.add_contact.AddContactScreen
import com.example.nexcontacts.ui.add_contact.SuccessScreen
import com.example.nexcontacts.ui.common.SnackbarController
import com.example.nexcontacts.ui.components.snackbar.CustomSnackbar
import com.example.nexcontacts.ui.contact_profile.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    SnackbarController.init(snackbarHostState, scope)

    Scaffold(
        containerColor = Color.Transparent,
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                CustomSnackbar(data)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.CONTACTS,
            modifier = Modifier.padding(padding)
        ) {

            // CONTACT LIST
            composable(Routes.CONTACTS) {
                ContactsScreen(
                    onAddClicked = {
                        navController.navigate(Routes.ADD_CONTACT)
                    },
                    onProfileClicked = { userId, editModeOpen ->
                        navController.navigate(Routes.profile(userId, editModeOpen))
                    }
                )
            }

            // ADD CONTACT
            composable(Routes.ADD_CONTACT) {
                AddContactScreen(
                    onCancel = { navController.popBackStack() },
                    onDone = {
                        SnackbarController.show("User is added to your phone!")
                        navController.navigate(Routes.SUCCESS)
                    }
                )
            }

            // SUCCESS SCREEN (after add)
            composable(Routes.SUCCESS) {
                SuccessScreen(navController)
            }

            // PROFILE SCREEN — userId parameter required
            composable(Routes.PROFILE) { backStackEntry ->

                val userId = backStackEntry.arguments?.getString("userId") ?: ""
                val editModeOpen = backStackEntry.arguments?.getString("editModeOpen")?.toBoolean() ?: false

                ProfileScreen(
                    userId = userId,
                    editModeOpen = editModeOpen,
                    onCancel = { navController.popBackStack() }
                )
            }

        }

    }

}
