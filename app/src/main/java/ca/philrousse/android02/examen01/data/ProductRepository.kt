package ca.philrousse.android02.examen01.data

import androidx.annotation.WorkerThread
import ca.philrousse.android02.examen01.generic.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProduitsRepository(produitDao: ProduitDAO):BaseRepository<Produit>(produitDao) {

    //val get: Flow<List<Produit>> = produitDao.get()

    /*
    fun listeProduitsParCategorie(cate:String) = produitDao.obtenirListeProduitsParCategorie(cate)

    fun totalInventaire(notUser:Any?): Flow<TotalInventaire> = produitDao.obtenirValeurInventaire()

    fun totalInventaire(cate:String): Flow<TotalInventaire> = produitDao.obtenirValeurInventaireParCategorie(cate)

    val listeCategories = produitDao.obtenirListeCategory().map {
        it.map { categorie -> categorie.categ }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(produit: Produit):Long {
        return produitDao.insert(produit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(produit: Produit) {
        produitDao.delete(produit)
    }

     */
}