package ca.philrousse.android02.examen01.data

import ca.philrousse.android02.examen01.generic.BaseRepository
import ca.philrousse.android02.examen01.generic.ComparisonOperators
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map


class EmployeeRepository(employesDAO: EmployesDAO):BaseRepository<Employes>(employesDAO) {
    fun filterSalaire(salaire:Int,comparisonOperators: ComparisonOperators): Flow<List<Employes>> {
        return get.map { list->
            list.filter {
                when (comparisonOperators){
                    ComparisonOperators.EqualTo -> it.salaire == salaire
                    ComparisonOperators.LessThan -> it.salaire < salaire
                    ComparisonOperators.LessThanOrEqualTo -> it.salaire <= salaire
                    ComparisonOperators.GreaterThan -> it.salaire > salaire
                    ComparisonOperators.GreaterThanOrEqualTo -> it.salaire >= salaire
                }
            }
        }
    }
}