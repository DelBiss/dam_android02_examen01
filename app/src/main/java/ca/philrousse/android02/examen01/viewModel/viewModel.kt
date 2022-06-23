package ca.philrousse.android02.examen01.viewModel

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ca.philrousse.android02.examen01.data.EmployeeRepository
import ca.philrousse.android02.examen01.data.Employes
import ca.philrousse.android02.examen01.generic.BaseViewModel
import ca.philrousse.android02.examen01.generic.ComparisonOperators
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class EmployesViewModel(private val repository: EmployeeRepository): BaseViewModel<Employes>(repository),
    SearchView.OnQueryTextListener{

    override fun get(): StateFlow<List<Employes>> {
        return salaryFilter.flatMapLatest { salary ->
            if(salary == null){
                super.get()
            } else {
                filterSalaire(salary)
            }
        }.stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    }
    private var salaryFilter = MutableStateFlow<Int?>(null)
    fun filterSalaire(salaire:Int, comparisonOperators: ComparisonOperators = ComparisonOperators.GreaterThanOrEqualTo): Flow<List<Employes>> {
        return repository.filterSalaire(salaire, comparisonOperators)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("Search", "Submited: $query")
        var searchSalary = query?.toIntOrNull()
        salaryFilter.value = searchSalary

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("Search", "Change: $newText")
        var searchSalary = newText?.toIntOrNull()
        salaryFilter.value = searchSalary

        return false
    }
}

class EmployesViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}