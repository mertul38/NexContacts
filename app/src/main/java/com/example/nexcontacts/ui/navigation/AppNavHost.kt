package com.example.nexcontacts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nexcontacts.ui.contacts.ContactsScreen
import com.example.nexcontacts.ui.add_contact.AddContactScreen
import com.example.nexcontacts.ui.add_contact.SuccessScreen
import com.example.nexcontacts.ui.contact_profile.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.CONTACTS
    ) {

        // CONTACT LIST
        composable(Routes.CONTACTS) {
            ContactsScreen(
                onAddClicked = {
                    navController.navigate(Routes.ADD_CONTACT)
                },
                onProfileClicked = { userId ->
                    navController.navigate(Routes.profile(userId))
                }
            )
        }

        // ADD CONTACT
        composable(Routes.ADD_CONTACT) {
            AddContactScreen(
                onCancel = { navController.popBackStack() },
                onDone = {
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

            ProfileScreen(
                userId = userId,
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
