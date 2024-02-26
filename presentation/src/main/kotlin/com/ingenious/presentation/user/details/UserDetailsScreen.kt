package com.ingenious.presentation.user.details

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ingenious.presentation.SingleEventEffect
import com.ingenious.presentation.theme.Dimens
import presentation.BuildConfig
import presentation.R

@Composable
fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: UserDetailsViewModel,
    close: () -> Unit
) {
    val context = LocalContext.current
    val errorMessage = stringResource(id = R.string.common_error_occurred)

    SingleEventEffect(sideEffectFlow = viewModel.error) { throwable ->
        val message = if (BuildConfig.DEBUG) throwable.localizedMessage else errorMessage
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        close()
    }

    val details by viewModel.details.collectAsStateWithLifecycle()

    UserDetailsScreen(
        modifier = modifier,
        details = details
    )
}

@Composable
private fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    details: Details
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(256.dp),
            model = details.avatarUrl,
            contentDescription = details.userName + stringResource(id = R.string.user_list_avatar),
        )

        Spacer(modifier = Modifier.height(16.dp))

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_id),
            info = details.id
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_node_id),
            info = details.nodeId
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_username),
            info = details.userName
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_name),
            info = details.name
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_type),
            info = details.type
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_company),
            info = details.company
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_email),
            info = details.email
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_created_at),
            info = details.createdAt
        )

        DetailsInfoRow(
            label = stringResource(id = R.string.user_details_updated_at),
            info = details.updatedAt
        )
    }
}

@Composable
private fun DetailsInfoRow(
    modifier: Modifier = Modifier,
    label: String,
    info: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "$label:",
            fontSize = Dimens.userDetailsInfoTextSize,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(2.5f),
            text = info,
            fontSize = Dimens.userDetailsInfoTextSize,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
