package ca.philrousse.android02.examen01.generic

import androidx.annotation.WorkerThread
import ca.philrousse.android02.examen01.data.Produit
import kotlinx.coroutines.flow.Flow

enum class ComparisonOperators{
    GreaterThan,
    LessThan,
    GreaterThanOrEqualTo,
    LessThanOrEqualTo,
    EqualTo
}

open class BaseRepository<T>(protected val dao:BaseDao<T>) {
    val get: Flow<List<T>> = dao.get()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: T):Long {
        return dao.insert(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(item: T) {
        dao.delete(item)
    }
}