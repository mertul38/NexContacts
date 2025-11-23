package com.example.nexcontacts.ui.contact_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.nexcontacts.ui.common_components.CommonPhotoPickerBottomSheet
import com.example.nexcontacts.ui.contact_profile.components.topbar.DeleteModalSheet
import com.example.nexcontacts.ui.contacts.contact_profile.components.ProfileTopBar
import com.example.nexcontacts.ui.theme.AppTheme
import com.example.nexcontacts.utils.ImageUtils

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

    // CAMERA launcher
    val cameraUri = remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cameraUri.value != null) {
            viewModel.updatePhoto(cameraUri.value.toString())
        }
    }

    // GALLERY launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { viewModel.updatePhoto(it.toString()) }
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
                photoUri = state.newPhotoUri ?: user.remoteImageUrl,
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
        DeleteModalSheet(
            onDeleteClicked = {
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
            onCancelClicked = {
                showRemoveSheet = false
            },
            onDismissRequest = {
                showRemoveSheet = false
            }
        )
    }

    CommonPhotoPickerBottomSheet(
        show = showPhotoPicker,
        onDismiss = { showPhotoPicker = false },
        onCamera = {
            val uri = ImageUtils.createImageUri(context)
            cameraUri.value = uri
            cameraLauncher.launch(uri)
        },
        onGallery = {
            galleryLauncher.launch("image/*")
        },
        onCancel = { showPhotoPicker = false }
    )


}

