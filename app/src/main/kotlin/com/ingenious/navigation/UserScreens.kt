package com.ingenious.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ingenious.presentation.user.details.UserDetailsScreen
import com.ingenious.presentation.user.details.UserDetailsViewModel
import com.ingenious.presentation.user.list.UserListScreen
import com.ingenious.presentation.user.list.UserListViewModel

private const val PARAM_USERNAME = "username"

internal enum class UsersScreen {
    List,
    Details
}

internal fun UsersScreen.toPath(): String {
    return "users/" + when (this) {
        UsersScreen.List -> "list"
        UsersScreen.Details -> "details"
    }
}

internal fun UsersScreen.toRoute(): String {
    return this.toPath() + when (this) {
        UsersScreen.List -> ""
        UsersScreen.Details -> "?$PARAM_USERNAME={$PARAM_USERNAME}"
    }
}

internal fun NavGraphBuilder.usersNavGraph(navController: NavController) {
    composable(route = UsersScreen.List.toRoute()) {
        val viewModel = hiltViewModel<UserListViewModel>()

        UserListScreen(
            viewModel = viewModel,
            onClickItem = { userName ->
                val target = UsersScreen.Details.toPath() + "?$PARAM_USERNAME=$userName"
                navController.navigate(target)
            }
        )
    }

    composable(
        route = UsersScreen.Details.toRoute(),
        arguments = listOf(navArgument(PARAM_USERNAME) { type = NavType.StringType })
    ) { backStack ->
        val userName = backStack.arguments?.getString(PARAM_USERNAME)
        requireNotNull(userName) { "nav param $PARAM_USERNAME not found" }

        val viewModel = hiltViewModel(
            key = UserDetailsViewModel::class.java.name + "#$userName",
            creationCallback = { factory: UserDetailsViewModel.Factory ->
                factory.create(userName)
            }
        )

        UserDetailsScreen(
            viewModel = viewModel,
            close = { navController.popBackStack() }
        )
    }
}