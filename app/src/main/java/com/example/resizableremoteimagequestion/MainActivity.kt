package com.example.resizableremoteimagequestion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resizableremoteimagequestion.ui.theme.ResizableRemoteImageQuestionTheme
import coil.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResizableRemoteImageQuestionTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    Column {
        Text("This text should be below the image.")

        val painter =
            rememberImagePainter("https://www.healthpartners.com/ucm/groups/public/@hp/@public/documents/documents/entry_217455.jpg")
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .then( // https://stackoverflow.com/a/68848814/2852849
                    (painter.state as? ImagePainter.State.Success)
                        ?.painter
                        ?.intrinsicSize
                        ?.let { intrinsicSize ->
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(intrinsicSize.width / intrinsicSize.height)
                        } ?: Modifier
                )
        )

        Text("This text should be below the image.")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ResizableRemoteImageQuestionTheme {
        MainContent()
    }
}