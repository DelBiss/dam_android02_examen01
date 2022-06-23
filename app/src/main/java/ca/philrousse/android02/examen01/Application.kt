package ca.philrousse.android02.examen01

import android.app.Application
import android.content.Context
import android.util.Log
import ca.philrousse.android02.examen01.data.EmployeeRepository
import ca.philrousse.android02.examen01.data.EmployesRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EmployesApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { EmployesRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { EmployeeRepository(database.dao()) }

}