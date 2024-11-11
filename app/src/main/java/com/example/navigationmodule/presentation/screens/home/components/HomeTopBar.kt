package com.example.navigationmodule.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationmodule.R
import com.example.navigationmodule.presentation.ui.components.avatars.Avatar
import com.example.navigationmodule.presentation.ui.theme.heading2Xs
import com.example.navigationmodule.presentation.ui.theme.headingLg

@Composable
fun HomeTopBar(
    onProfileClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text("Service Unavailable", style = MaterialTheme.typography.headingLg)
            Spacer(Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Section 3, HSR Layout, Bangalore, Karnataka",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }

        Avatar(
            res = R.drawable.ic_user,
            size = 45.dp,
            backgroundColor = Color.Gray,
            iconColor = Color.DarkGray,
            onClick = {
                onProfileClick()
            })
    }

}

@Preview
@Composable
private fun HomeTopBarPreview() {
    HomeTopBar(onProfileClick = {})
}