package ca.philrousse.android02.examen01.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Employes::class], version = 1, exportSchema = false)
abstract class EmployesRoomDatabase: RoomDatabase() {
    abstract fun dao(): EmployesDAO

    companion object {
        @Volatile
        private var INSTANCE: EmployesRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EmployesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployesRoomDatabase::class.java,
                    "bdemployes"
                ).addCallback(EmployesDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class EmployesDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.dao())
                    }
                }
            }

            fun populateDatabase(dao: EmployesDAO) {
                dao.deleteAll()

                dao.insert(Employes(1,"Lalonde","Karl", "M", "Ouvrier",37100))
                dao.insert(Employes(2,"Savoie","Jean","M","Vendeur",31500))
                dao.insert(Employes(3,"St-Pierre","Aline","F","Secrétaire",22500))
                dao.insert(Employes(4,"Dupuis","Josée","F","Vendeur",22500))
                dao.insert(Employes(5,"Bibeau","Rita","F","Administrateur",27000))
                dao.insert(Employes(6,"Gingras","Marc","M","Administrateur",40500))
                dao.insert(Employes(7,"Cardinal","Paul","M","Ouvrier",20000))
                dao.insert(Employes(8,"Thibault","Gratien","M","Administrateur",32000))

            }
        }
    }
}