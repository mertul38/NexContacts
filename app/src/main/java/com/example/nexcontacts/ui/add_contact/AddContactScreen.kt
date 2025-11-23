package com.example.nexcontacts.ui.add_contact

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexcontacts.ui.add_contact.components.topbar.AddContactTopBar
import com.example.nexcontacts.ui.common_components.CommonInputInfoSection
import com.example.nexcontacts.ui.common_components.CommonInputPhotoSection
import com.example.nexcontacts.ui.common_components.CommonPhotoPickerBottomSheet
import com.example.nexcontacts.ui.theme.AppTheme
import com.example.nexcontacts.utils.ImageUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    viewModel: AddContactViewModel = viewModel(),
    onCancel: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    val state = viewModel.state
    var showPhotoPicker by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val cameraUri = remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cameraUri.value != null) {
            viewModel.onPhotoSelected(cameraUri.value.toString())
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { viewModel.onPhotoSelected(it.toString()) }
    }

    Scaffold(
        containerColor = AppTheme.colors.background,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(padding)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            val context = LocalContext.current
            AddContactTopBar(
                onCancel = onCancel,
                onDone = {
                    viewModel.onDone(
                        context = context,
                        onSuccess = onDone,
                        onError = { msg -> scope.launch { snackbarHostState.showSnackbar(msg) } }
                    )
                }
            )

            Spacer(modifier = Modifier.height(64.dp))

            CommonInputPhotoSection(
                photoUri = state.photoUri,
                editMode = true,
                onPhotoClick = { showPhotoPicker = true }
            )

            Spacer(modifier = Modifier.height(32.dp))

            CommonInputInfoSection(
                firstName = state.firstName,
                lastName = state.lastName,
                phone = state.phone,
                editMode = true,
                onFirstNameChange = viewModel::onFirstNameChanged,
                onLastNameChange = viewModel::onLastNameChanged,
                onPhoneChange = viewModel::onPhoneChanged
            )
        }
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
