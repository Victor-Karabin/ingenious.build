package com.ingenious.presentation.user.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import presentation.BuildConfig
import presentation.R

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel,
    onClickItem: (userName: String) -> Unit
) {
    val userItems = viewModel.userItems.collectAsLazyPagingItems()

    UserListScreen(
        modifier = modifier,
        items = userItems,
        onClickItem = { item -> onClickItem(item.userName) }
    )
}

@Composable
private fun UserListScreen(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<UserItem>,
    onClickItem: (UserItem) -> Unit
) {
    LazyColumn(modifier = modifier) {

        items(
            count = items.itemCount,
            key = { index ->
                val item = requireNotNull(items[index]) { "item #$index not found" }
                item.id
            }
        ) { index ->
            val item = requireNotNull(items[index]) { "item #$index not found" }

            UserItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                item = item,
                onClick = { onClickItem(item) }
            )
        }

        when {
            items.loadState.refresh is LoadState.Loading -> {
                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
            }

            items.loadState.refresh is LoadState.Error -> {
                item {
                    ErrorItem(
                        modifier = Modifier.fillMaxWidth(),
                        error = items.loadState.refresh as LoadState.Error,
                        onClickRetry = { items.retry() }
                    )
                }
            }

            items.loadState.append is LoadState.Loading -> {
                item { LoadingItem(modifier = Modifier.fillMaxWidth()) }
            }

            items.loadState.append is LoadState.Error -> {
                item {
                    ErrorItem(
                        modifier = Modifier.fillMaxWidth(),
                        error = items.loadState.refresh as LoadState.Error,
                        onClickRetry = { items.retry() }
                    )
                }
            }
        }
    }
}

@Composable
private fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.common_loading),
            color = MaterialTheme.colorScheme.primary
        )
        CircularProgressIndicator(Modifier.padding(top = 12.dp))
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    item: UserItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                onClick { onClick(); true }
            }
            .then(modifier)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(fraction = 1f),
            model = item.avatarUrl,
            contentDescription = item.userName + stringResource(id = R.string.user_list_avatar),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.userName,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = item.userType,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun LoadingItem(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
private fun ErrorItem(
    modifier: Modifier = Modifier,
    error: LoadState.Error,
    onClickRetry: () -> Unit
) {
    val throwable = error.error
    val message = if (BuildConfig.DEBUG) {
        throwable.localizedMessage ?: "$throwable"
    } else {
        stringResource(id = R.string.common_error_occurred)
    }

    ErrorItem(
        modifier = modifier,
        message = message,
        onClickRetry = onClickRetry
    )
}

@Composable
private fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = message,
            color = MaterialTheme.colorScheme.error
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(
                text = stringResource(id = R.string.common_retry),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}