package com.abachta.jetpacktutorial.courses.advanced

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.camera.view.video.AudioConfig
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.abachta.jetpacktutorial.PermissionResult
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File

private fun hasCameraPermission(context: Context) =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

private fun hasMicrophonePermission(context: Context) =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED

private val recordingPermissions = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.RECORD_AUDIO,
)

private val camera_1 = LessonPage (
   headingResId = R.string.camera_1_heading
) {

    CodeListing(
        code = """
            dependencies {
                // ...
                val cameraxVersion = "1.3.4"
                implementation ("androidx.camera:camera-core:${'$'}cameraxVersion")
                implementation ("androidx.camera:camera-camera2:${'$'}cameraxVersion")
                implementation ("androidx.camera:camera-view:${'$'}cameraxVersion")
                implementation ("androidx.camera:camera-lifecycle:${'$'}cameraxVersion")
            }
        """.trimIndent()
    )
}

private val camera_2 = LessonPage (
   headingResId = R.string.camera_2_heading
) {

    CodeListing(
        code = """
            val context = LocalContext.c-current
            val lifecycleOwner = LocalLifecycleOwner.c-current

            val cameraController = remember {
                LifecycleCameraController(context).apply {
                    setEnabledUseCases(
                        CameraController.IMAGE_CAPTURE
                    )
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            c-AndroidView(
                factory = {
                    PreviewView(it).apply {
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)

                        scaleType = PreviewView.ScaleType.FIT_CENTER
                    }
                }
            )
        """.trimIndent()
    )

    if (!isCurrentPage) return@LessonPage

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }

    var hasPermission by remember { mutableStateOf(hasCameraPermission(context)) }

    LaunchedEffect(Unit) {
        it.permissionResults.collect { result ->
            if (result is PermissionResult.Granted &&
                result.permission.name == Manifest.permission.CAMERA) {
                hasPermission = true
            }
        }
    }

    if (!hasPermission) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                it.requestPermission(Manifest.permission.CAMERA)
            }
        ) {
            ResText(R.string.camera_request_permission)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        if (hasPermission) {
            AndroidView(
                factory = {
                    PreviewView(it).apply {
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)

                        scaleType = PreviewView.ScaleType.FIT_CENTER
                    }
                },
                modifier = Modifier.size(300.dp)
            )
        } else {
            ResText(
                resId = R.string.camera_no_permissions,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val camera_3 = LessonPage (
   headingResId = R.string.camera_3_heading
) {

    CodeListing(
        code = """
            c-Button(
                onClick = {
                    cameraController.cameraSelector =
                        if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }
                }
            ) { ... }
        """.trimIndent()
    )

    if (!isCurrentPage) return@LessonPage

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }

    var hasPermission by remember { mutableStateOf(hasCameraPermission(context)) }

    LaunchedEffect(Unit) {
        it.permissionResults.collect { result ->
            if (result is PermissionResult.Granted &&
                result.permission.name == Manifest.permission.CAMERA) {
                hasPermission = true
            }
        }
    }

    if (!hasPermission) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                it.requestPermission(Manifest.permission.CAMERA)
            }
        ) {
            ResText(R.string.camera_request_permission)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        if (this@LessonPage.isCurrentPage) {
            if (hasPermission) {
                AndroidView(
                    factory = {
                        PreviewView(it).apply {
                            controller = cameraController
                            cameraController.bindToLifecycle(lifecycleOwner)

                            scaleType = PreviewView.ScaleType.FIT_CENTER

                        }
                    },
                    modifier = Modifier.size(300.dp)
                )

            } else {
                ResText(
                    resId = R.string.camera_no_permissions,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        enabled = hasPermission,
        onClick = {
            cameraController.cameraSelector =
                if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    CameraSelector.DEFAULT_FRONT_CAMERA
                } else {
                    CameraSelector.DEFAULT_BACK_CAMERA
                }
        }
    ) {
        ResText(R.string.camera_switch)
    }
}

private fun takePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    cameraController.takePicture(
        ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback() {
            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }

            override fun onCaptureSuccess(image: ImageProxy) {
                val bitmap = image.toBitmap()

                val scaledBitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    300,
                    400,
                    true
                )

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }

                val rotatedBitmap = Bitmap.createBitmap(
                    scaledBitmap,
                    0,
                    0,
                    scaledBitmap.width,
                    scaledBitmap.height,
                    matrix,
                    true
                )

                image.close()

                onPhotoTaken(rotatedBitmap)
            }
        }
    )
}

@Composable
private fun PhotoBottomSheet(
    bitmaps: List<Bitmap>
) {
    if (bitmaps.isEmpty()) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            ResText(R.string.camera_gallery_no_photos)
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
        ) {
            items(bitmaps) {bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private val camera_4 = LessonPage (
   headingResId = R.string.camera_4_heading
) {

    if (!isCurrentPage) return@LessonPage

    CodeListing(
        code = """
            cameraController.takePicture(
                ContextCompat.getMainExecutor(context),
                object : OnImageCapturedCallback() {
                    override fun onError(exception: ImageCaptureException) {
                        // handle the error
                    }

                    override fun onCaptureSuccess(image: ImageProxy) {
                        val bitmap = image.toBitmap()
                        // use the bitmap
                    }
                }
            )
        """.trimIndent()
    )

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }

    var hasPermission by remember { mutableStateOf(hasCameraPermission(context)) }

    LaunchedEffect(Unit) {
        it.permissionResults.collect { result ->
            if (result is PermissionResult.Granted &&
                result.permission.name == Manifest.permission.CAMERA) {
                hasPermission = true
            }
        }
    }

    if (!hasPermission) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                it.requestPermission(Manifest.permission.CAMERA)
            }
        ) {
            ResText(R.string.camera_request_permission)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        if (hasPermission) {
            AndroidView(
                factory = {
                    PreviewView(it).apply {
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)

                        scaleType = PreviewView.ScaleType.FIT_CENTER

                    }
                },
                modifier = Modifier.size(300.dp)
            )
        } else {
            ResText(
                resId = R.string.camera_no_permissions,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    val scope = rememberCoroutineScope()
    val photos = remember { MutableStateFlow<List<Bitmap>>(emptyList()) }
    val photosState by photos.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val errorMessage = stringResource(R.string.camera_capture_error)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton (
            enabled = hasPermission,
            onClick = {
                takePhoto(
                    context = context,
                    cameraController = cameraController,
                    onPhotoTaken = {
                        photos.value += it
                    },
                    onError = { exception ->
                        scope.launch {
                            SnackbarController.sendEvent(SnackbarEvent(
                                message = exception.localizedMessage ?: errorMessage,
                                duration = SnackbarDuration.Long
                            ))
                        }
                    }
                )
            }
        ) {
            Icon (
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = null
            )
//            ResText(R.string.camera_take_photo)
        }

        IconButton(onClick = {
            showBottomSheet = true
        }) {
            Icon(
                imageVector = Icons.Default.Photo,
                contentDescription = null
            )
//            ResText(R.string.camera_open_gallery)
        }

        IconButton(
            enabled = hasPermission,
            onClick = {
                cameraController.cameraSelector =
                    if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    } else CameraSelector.DEFAULT_BACK_CAMERA
            }
        ) {
            Icon(
                imageVector = Icons.Default.Cameraswitch,
                contentDescription = null
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            PhotoBottomSheet(
                bitmaps = photosState
            )
        }
    }
}

private val camera_5 = LessonPage (
   headingResId = R.string.camera_5_heading
) {

    CodeListing(
        code = """
            override fun onCaptureSuccess(image: ImageProxy) {
                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                
                // use the rotated bitmap
            }
        }
        """.trimIndent()
    )
}

private var recording by mutableStateOf<Recording?>(null)

@SuppressLint("MissingPermission")
private fun recordVideo(
    context: Context,
    controller: LifecycleCameraController,
    successMessage: String,
    errorMessage: String
) {
    if (recording != null) {
        recording?.stop()
        recording = null
        return
    }

    val outputFile = File(context.filesDir, "recording.mp4")
    val listener = { event: VideoRecordEvent ->
        when(event) {
            is VideoRecordEvent.Finalize -> {
                if(event.hasError()) {
                    recording?.close()
                    recording = null

                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        successMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    recording = controller.startRecording(
        FileOutputOptions.Builder(outputFile).build(),
        AudioConfig.create(true),
        ContextCompat.getMainExecutor(context),
        listener
    )
}

private val camera_6 = LessonPage (
   headingResId = R.string.camera_6_heading
) {

    CodeListing(
        code = """
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        """.trimIndent()
    )

    CodeListing(
        code = """
            var recording: Recording? = null
            
            fun recordVideo(
                context: Context,
                controller: LifecycleCameraController
            ) {
                if (recording != null) {
                    recording?.stop()
                    recording = null
                    return
                }
                
                val outputFile = File(context.filesDir, "recording.mp4")
                val listener: (VideoRecordEvent) -> Unit = ...
                
                recording = controller.startRecording(
                    FileOutputOptions.Builder(outputFile).build(),
                    AudioConfig.create(true),
                    ContextCompat.getMainExecutor(context),
                    listener
                )
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            val listener = { event: VideoRecordEvent ->
                when(event) {
                    is VideoRecordEvent.Finalize -> {
                        if(event.hasError()) {
                        recording?.close()
                        recording = null
    
                        // Capture failed
                    } else {
                        // Capture succeeded
                    }
                }
            }
        """.trimIndent()
    )

    if (!isCurrentPage) return@LessonPage

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }

    var hasCameraPermission by remember { mutableStateOf(hasCameraPermission(context)) }
    var hasMicrophonePermission by remember { mutableStateOf(hasMicrophonePermission(context)) }

    val hasPermissions = hasCameraPermission && hasMicrophonePermission

    LaunchedEffect(Unit) {
        it.permissionResults.collect { result ->
            if (result is PermissionResult.Granted &&
                result.permission.name == Manifest.permission.CAMERA) {
                hasCameraPermission = true
            }

            if (result is PermissionResult.Granted &&
                result.permission.name == Manifest.permission.RECORD_AUDIO) {
                hasMicrophonePermission = true
            }
        }
    }

    if (!hasPermissions) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                it.requestPermissions(recordingPermissions)
            }
        ) {
            ResText(R.string.camera_recording_request_permissions)
        }
    }

    Preview(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        if (hasPermissions) {
            AndroidView(
                factory = {
                    PreviewView(it).apply {
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)

                        scaleType = PreviewView.ScaleType.FIT_CENTER
                    }
                },
                modifier = Modifier.size(300.dp)
            )
        } else {
            ResText(
                resId = R.string.camera_recording_no_permissions,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    val successMessage = stringResource(R.string.capture_succeeded)
    val errorMessage = stringResource(R.string.capture_failed)

    Button(
        onClick = {
            recordVideo(
                context = context,
                controller = cameraController,
                successMessage = successMessage,
                errorMessage = errorMessage
            )
        },
        enabled = hasPermissions,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        val text = if (recording == null) {
            stringResource(R.string.camera_start_recording)
        } else stringResource(R.string.camera_stop_recording)

        Text(text)
    }
}

val cameraPages = listOf(
    camera_1,
    camera_2,
    camera_3,
    camera_4,
    camera_5,
    camera_6
)