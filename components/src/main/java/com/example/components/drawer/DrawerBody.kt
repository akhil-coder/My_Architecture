package com.example.components.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.buttons.CustomButton
import com.example.components.util.MenuItem

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    logOut: () -> Unit,
    onItemClick: (MenuItem) -> Unit
) {

    Column {
        LazyColumn(modifier = modifier.weight(1f)) {
            items(items) { item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
                        }
                        .padding(16.dp)
                ) {
                    Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        style = itemTextStyle,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Box(
            Modifier.fillMaxWidth(0.8f).padding(12.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CustomButton(text = "Logout") {
                logOut()
            }
        }
    }


    
}