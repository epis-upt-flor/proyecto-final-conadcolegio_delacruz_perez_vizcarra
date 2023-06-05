package com.example.controladmin.presentation.personal_list.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PersonalList(
    state: PersonalListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = refreshData
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.personal
                ){personal ->

                    var isDeleted by remember { mutableStateOf(false) }
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            Log.d("PersonalList", "Dismiss value: ${it.name}")
                            if(it == DismissedToEnd) isDeleted = !isDeleted
                            it != DismissedToEnd
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(StartToEnd),
                        dismissThresholds = {
                            FractionalThreshold(0.5f)
                        },
                        background = {
                            val direction = dismissState.dismissDirection ?:  return@SwipeToDismiss
                            val color by animateColorAsState(
                                when(dismissState.targetValue) {
                                    Default -> Color.LightGray
                                    DismissedToEnd -> Color.Red
                                    DismissedToStart -> Color.Red
                                }
                            )
                            val alignment = when (direction) {
                                StartToEnd -> Alignment.CenterStart
                                EndToStart -> Alignment.CenterEnd
                            }
                            val icon = when (direction) {
                                StartToEnd -> Icons.Default.Delete
                                EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        }
                    ) {
                        if(isDeleted) {
                            // TODO("DELETE BOOK")
                        } else {
                            PersonalListItem(personal)
                        }
                    }
                }
            }
        }

        if (state.error.isNotBlank()){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}