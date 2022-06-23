package ca.philrousse.android02.examen01.data

import androidx.room.Dao
import androidx.room.Query
import ca.philrousse.android02.examen01.generic.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EmployesDAO: BaseDao<Employes>("Employes") {
    @Query("SELECT * FROM Employes")
    abstract override fun get(): Flow<List<Employes>>
}