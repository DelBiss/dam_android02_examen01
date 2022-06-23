package ca.philrousse.android02.examen01.generic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.philrousse.android02.examen01.data.Produit
import ca.philrousse.android02.examen01.data.ProduitsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModel<T>(private val repository: BaseRepository<T>): ViewModel() {
    open fun get()
    : StateFlow<List<T>> {
        return repository.get.stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun insert(item: T, callback: ((Long)->Unit)? = null) = CoroutineScope(Dispatchers.IO).launch {
        val insertedId = repository.insert(item)
        callback?.let {
            it(insertedId)
        }
    }

    fun delete(item: T) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }
}