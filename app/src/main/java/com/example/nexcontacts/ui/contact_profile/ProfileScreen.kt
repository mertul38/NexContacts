package com.example.nexcontacts.ui.contact_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexcontacts.ui.common_components.CommonInputInfoSection
import com.example.nexcontacts.ui.common_components.CommonInputPhotoSection
import com.example.nexcontacts.ui.contacts.contact_profile.components.ProfileTopBar
import com.example.nexcontacts.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userId: String,
    onCancel: () -> Unit,
    viewModel: ProfileScreenViewModel = viewModel()
) {
    val state by viewModel.state

    var showRemoveSheet by remember { mutableStateOf(false) }
    var showPhotoPicker by remember { mutableStateOf(false) }

    val user = state.user     // <-- user artık tüm composable boyunca var

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val event = viewModel.event.value

    LaunchedEffect(event) {
        when (event) {
            ProfileEvent.NavigateBack -> {
                onCancel()   // ContactsScreen'e dön
                viewModel.consumeEvent()
            }
            is ProfileEvent.ShowError -> {
                snackbarHostState.showSnackbar(event.message)
                viewModel.consumeEvent()
            }
            null -> Unit
        }
    }

    LaunchedEffect(userId) {
        viewModel.loadUser(userId)
    }

    Scaffold(
        containerColor = AppTheme.colors.background,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        if (state.isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            return@Scaffold
        }

        if (user == null) return@Scaffold

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(padding)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            ProfileTopBar(
                editMode = state.editMode,
                onCancel = { viewModel.toggleEditMode() },
                onEditClicked = { viewModel.toggleEditMode() },
                onDone = {
                    viewModel.saveChanges()
                },
                onRemoveClicked = { showRemoveSheet = true }
            )

            Spacer(modifier = Modifier.height(32.dp))

            CommonInputPhotoSection(
                photoUri = user.profileImageUrl,
                editMode = state.editMode,
                onPhotoClick = { showPhotoPicker = true }
            )

            Spacer(modifier = Modifier.height(32.dp))

            CommonInputInfoSection(
                firstName = user.firstName,
                lastName = user.lastName,
                phone = user.phoneNumber,
                editMode = state.editMode,
                onFirstNameChange = viewModel::updateFirstName,
                onLastNameChange = viewModel::updateLastName,
                onPhoneChange = viewModel::updatePhone
            )
        }
    }

    // --- REMOVE SHEET ---
    if (showRemoveSheet && user != null) {
        ModalBottomSheet(
            onDismissRequest = { showRemoveSheet = false }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Delete Contact?", style = MaterialTheme.typography.titleLarge)

                Spacer(Modifier.height(12.dp))

                Text(
                    "Are you sure you want to delete this contact?",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.removeUser(
                            user.id,
                            onSuccess = {
                                showRemoveSheet = false
                                onCancel()
                            },
                            onError = {
                                showRemoveSheet = false
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete")
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { showRemoveSheet = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

