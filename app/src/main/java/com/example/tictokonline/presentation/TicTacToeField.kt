package com.example.tictokonline.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictokonline.data.GameState


@Composable
fun TicTacToeField(
    state: GameState,
    modifier: Modifier = Modifier,
    playerXColor: Color = Color.Green,
    playerOColor: Color = Color.Red,
    onTapInField: (x: Int, y: Int) -> Unit
) {
    Canvas(modifier = modifier) {
        drawVerticalLine(size.width * 1 / 3)
        drawVerticalLine(size.width * 2 / 3)
        drawHorizontalLine(size.height * 1 / 3)
        drawHorizontalLine(size.height * 2 / 3)

         drawX(color = Color.Red ,  center = Offset(size.width * (1/ 6f) , size.height *(5/ 6f)))
         drawO(color = Color.Green , center = Offset(x = size.width /6 , y = size.height/2))
    }
}

private fun DrawScope.drawX(
    color: Color,
    center: Offset,
    size: Float = 20.dp.toPx()
) {
    drawLine(
        color,
        start = Offset(
            x = center.x - size,
            y = center.y - size
        ),
        end = Offset(
            x = center.x + size,
            y = center.y + size,
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    drawLine(
        color,
        start = Offset(
            x = center.x + size,
            y = center.y - size
        ),
        end = Offset(
            x = center.x - size,
            y = center.y + size,
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

}

private fun DrawScope.drawO(
    color: Color,
    center: Offset,
    size: Float = 20.dp.toPx()
) {

    drawCircle(
        color, center = center, radius = size, style = Stroke(width = 3.dp.toPx())
    )

}

private fun DrawScope.drawVerticalLine(startX: Float) {
    drawLine(
        color = Color.Black,
        start = Offset(
            x = startX,
            y = 0f
        ),
        end = Offset(
            x = startX,
            y = size.height
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawHorizontalLine(startY: Float) {
    drawLine(
        color = Color.Black,
        start = Offset(
            x = 0f,
            y = startY
        ),
        end = Offset(
            x = size.width,
            y = startY
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewTicTocToeField() {

    TicTacToeField(
        state = GameState(
            field = arrayOf(
                arrayOf( 'O'  , null , 'X'),
                arrayOf( null , null , null),
                arrayOf( null , null , null)
            )
        ),
        onTapInField = { x, y -> },
        modifier = Modifier.size(300.dp))
}