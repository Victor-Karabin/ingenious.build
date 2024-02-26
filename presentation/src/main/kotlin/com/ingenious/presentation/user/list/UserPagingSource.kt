package com.ingenious.presentation.user.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ingeniuos.common.requirePayload
import com.ingeniuos.common.toException
import javax.inject.Inject

internal class UserPagingSource @Inject constructor(
    private val requestPage: suspend (nextPage: Int, loadSize: Int) -> Result<List<UserItem>>
) : PagingSource<Int, UserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val result = requestPage(nextPageNumber, params.loadSize)
            if (result.isSuccess) {
                val users = result.requirePayload()

                LoadResult.Page(
                    data = users,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (users.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(result.toException())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}