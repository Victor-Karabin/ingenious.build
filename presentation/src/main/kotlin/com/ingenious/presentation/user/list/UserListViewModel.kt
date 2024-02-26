package com.ingenious.presentation.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ingenious.interactors.user.list.UserPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userPageUseCase: UserPageUseCase
) : ViewModel() {

    internal val userItems = Pager(
        config = PagingConfig(
            initialLoadSize = INITIAL_LOAD_SIZE,
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE
        ),
        pagingSourceFactory = {
            UserPagingSource { nextPage, loadSize ->
                userPageUseCase(nextPage, loadSize)
                    .map { users -> users.map { user -> user.toItem() } }
            }
        }
    ).flow.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = PAGE_SIZE
    }
}