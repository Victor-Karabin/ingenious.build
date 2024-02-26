package com.ingenious.presentation.user.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingenious.interactors.user.details.UserDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = UserDetailsViewModel.Factory::class)
class UserDetailsViewModel @AssistedInject constructor(
    @Assisted private val userName: String,
    private val detailsUseCase: UserDetailsUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(userId: String): UserDetailsViewModel
    }

    private val mutableDetails = MutableStateFlow(Details.EMPTY)
    internal val details = mutableDetails.asStateFlow()

    private val errorChannel = Channel<Throwable>(capacity = Channel.BUFFERED)
    internal val error: Flow<Throwable>
        get() = errorChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            detailsUseCase(userName)
                .onSuccess { user ->
                    mutableDetails.value = user.toDetails()
                }
                .onFailure { throwable ->
                    errorChannel.send(throwable)
                }
        }
    }
}