package ca.philrousse.android02.examen01.data

import androidx.room.*
import ca.philrousse.android02.examen01.generic.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProduitDAO:BaseDao<Produit>("Produits") {

    @Query("SELECT * FROM Produits")
    abstract override fun get(): Flow<List<Produit>>

/*
    @Query("SELECT * FROM Produits WHERE categ = :categ")
    fun obtenirListeProduitsParCategorie(categ:String): Flow<List<Produit>>

    @Query("SELECT SUM(qte * prix) AS total, 0 as isCategory FROM Produits")
    fun obtenirValeurInventaire(): Flow<TotalInventaire>

    @Query("SELECT SUM(qte * prix) AS total, 1 as isCategory FROM Produits WHERE categ = :categ")
    fun obtenirValeurInventaireParCategorie(categ:String): Flow<TotalInventaire>

    @Query("SELECT DISTINCT categ FROM Produits")
    fun obtenirListeCategory(): Flow<List<Categorie>>
*/


}