package com.example.controlepet.screens.cadastroUsuario

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.components.PhotoPickerField
import com.example.controlepet.viewModel.cadastroUsuario.RegisterUserViewModel

@Composable
fun RegisterUserScreen_step2(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: RegisterUserViewModel
) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            } else {
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
            selectedImageUri = it
            vm.saveLogo(context, bitmap)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text("Passo 2: Logo empresa / Foto")
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(12.dp))
        PhotoPickerField(
            selectedImageUri = selectedImageUri,
            onImageSelected = { selectedImageUri = it },
            launcher = launcher
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    vm.previousStep()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Voltar")
            }
            Button(
                onClick = {
                    vm.nextStep()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Seguir")
            }
        }
    }
}