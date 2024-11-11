package com.example.navigationmodule.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationmodule.R
import com.example.navigationmodule.presentation.ui.components.buttons.RoundedCornersButton
import com.example.navigationmodule.presentation.ui.theme.colorPrimary500
import com.example.navigationmodule.presentation.ui.theme.headingSm

@Composable
fun SectionNotAvailable(
    onRetryClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(160.dp),
            painter = painterResource(R.drawable.ic_not_available),
            contentDescription = null
        )

        Text(
            text = "Service Unavailable!",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(6.dp))

        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            text = "Apna Mart is not available at this location, please come back later",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        RoundedCornersButton(
            text = "Try Different Location",
            textStyle = MaterialTheme.typography.headingSm.copy(textAlign = TextAlign.Center),
            onClick = onRetryClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorPrimary500,
                contentColor = Color.White
            ),
        )
    }

}

@Preview
@Composable
private fun SectionNotAvailablePreview() {

    SectionNotAvailable(onRetryClick = {})

}