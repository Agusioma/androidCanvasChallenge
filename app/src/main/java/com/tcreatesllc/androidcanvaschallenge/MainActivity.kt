package com.tcreatesllc.androidcanvaschallenge

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.tcreatesllc.androidcanvaschallenge.ui.theme.AndroidCanvasChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        //Hide the status bars

        WindowCompat.setDecorFitsSystemWindows(window, true)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.systemBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        setContent {
            AndroidCanvasChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CameraFocusIndicator()
                }
            }
        }
    }
}

@Composable
fun brightnessCalibrator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(top = 5.dp, bottom = 5.dp)

    ) {
        Icon(
            painter = painterResource(id = R.drawable.crescent_moon_svgrepo_com),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 5.dp)
        )
        Text(
            text = "-2,3",
            modifier = Modifier
                .background(color = Color.LightGray)
                .align(Alignment.TopCenter)
                .padding(start = 3.dp, end = 3.dp),
            fontSize = 13.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.sun_2_svgrepo_com),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 5.dp)
        )
        thinEdgeBarRow(mods = Modifier.align(Alignment.BottomCenter))
        singleThinEdgeBar(mods = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun roundedEdgeBarRow() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(13.dp)
                .padding(5.dp)
        ) {
            var n = 16
            for (j in 0..n) {
                var i = (j * 20).toFloat()
                drawLine(
                    color = Color.White,
                    start = Offset(i, 0f),
                    end = Offset(i, size.height),
                    strokeWidth = 15f,
                    cap = StrokeCap.Round,
                )
            }

        }
    }
}

@Composable
fun sharpEdgeBarRow() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp)
        ) {
            var n = 85
            for (j in 0..n) {
                var i = (j * 12.5).toFloat()
                var barHeight = (1..6).random().toFloat()
                drawLine(
                    color = Color.White,
                    start = Offset(i, size.height),
                    end = Offset(i, size.height / barHeight),
                    strokeWidth = 8f,
                    cap = StrokeCap.Square,
                )
            }

        }
    }
}

@Composable
fun thinEdgeBarRow(mods: Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = mods.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(25.dp)
                .padding(start = 5.dp, end = 5.dp)
        ) {
            var n = 16
            for (j in 0..n) {
                //15 - the strokeWidth
                var i = (j * ((size.width / 15) - 0)).toFloat()

                drawLine(
                    color = Color.White,
                    start = Offset(i, 0f),
                    end = Offset(i, size.height),
                    strokeWidth = 2f,
                    cap = StrokeCap.Square,
                )
            }

        }
    }
}

@Composable
fun singleThinEdgeBar(mods: Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = mods.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .width(2.dp)
                .padding(start = 1.dp)
                .height(50.dp)
        ) {

            drawLine(
                color = Color.White,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = 2f,
                cap = StrokeCap.Square,
            )

        }
    }
}

@Composable
fun roundRect(width: Dp, height: Dp) {
    Canvas(
        modifier = Modifier
            .width(width)
            .height(height)
            .padding(30.dp)
            .rotate(-10f)
    ) {
        drawRoundRect(
            color = Color(0xFF2B292A),
            size = Size(width = size.width, height = size.height),
            cornerRadius = CornerRadius(x = 45.dp.toPx(), y = 45.dp.toPx())
        )
    }
}

@Composable
fun canvasText() {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(70.dp)
            .padding(20.dp)
            .rotate(-10f)
    ) {
        drawText(
            textMeasurer = textMeasurer,
            text = "Hello",
            style = TextStyle(color = Color.White)
        )
    }
}


@Composable
fun CameraFocusIndicator() {

    var cornerShape = with(LocalDensity.current) { 10.dp.toPx() }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .width(105.dp)
                .height(70.dp)
                .padding(10.dp)
        ) {
            var topLeftCorner = Path().apply {
                reset()
                arcTo(
                    rect = Rect(
                        offset = Offset(0f, 0f),
                        size = Size(cornerShape, cornerShape)
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(45f, 0f)
            }

            var topLeftCornerExtension = Path().apply {
                reset()
                moveTo(0f, 13f)

                lineTo(0f, 35f)
            }

            var bottomLeftCorner = Path().apply {

                reset()
                arcTo(
                    rect = Rect(
                        offset = Offset(0f, 120f),
                        size = Size(cornerShape, cornerShape)
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(0f, 115f)
            }

            var bottomLeftCornerExtension = Path().apply {
                reset()
                moveTo(13f, 147.8f)
                lineTo(45f, 147.8f)
            }

            var bottomRightCorner = Path().apply {
                reset()

                arcTo(
                    rect = Rect(
                        offset = Offset(200f, 120f),
                        size = Size(cornerShape, cornerShape)
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(185f, 147.8f)

            }

            var bottomRightCornerExtension = Path().apply {
                reset()
                moveTo(227.3f, 115f)
                lineTo(227.3f, 133.5f)
            }

            var topRightCorner = Path().apply {
                reset()

                arcTo(
                    rect = Rect(
                        offset = Offset(200f, 0f),
                        size = Size(cornerShape, cornerShape)
                    ),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(227.5f, 36f)

            }

            var topRightCornerExtension = Path().apply {
                reset()
                moveTo(214f, 0f)
                lineTo(185f, 0f)
            }

            var plusVerticalBar = Path().apply {
                reset()
                moveTo(115.5f, 55f)
                lineTo(116f, 94f)

            }

            var plusHorizontalBar = Path().apply {
                reset()
                moveTo(94.5f, 74f)

                lineTo(135.5f, 74f)

            }

            drawPath(
                topLeftCorner,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                topLeftCornerExtension,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                bottomLeftCorner,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                bottomLeftCornerExtension,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                bottomRightCorner,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                bottomRightCornerExtension,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                topRightCorner,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                topRightCornerExtension,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )


            drawPath(
                plusVerticalBar,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

            drawPath(
                plusHorizontalBar,
                color = Color.White,
                style = Stroke(
                    width = 2f,
                )
            )

        }
    }
}


@Preview(showBackground = false)
@Composable
fun AppPreview() {
    AndroidCanvasChallengeTheme {
        //roundRect(300.dp, 300.dp)
        // canvasText()
        brightnessCalibrator()
    }
}
