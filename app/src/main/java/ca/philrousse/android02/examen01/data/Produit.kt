package ca.philrousse.android02.examen01.data

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import ca.philrousse.android02.examen01.R

data class Categorie(val categ: String)

data class TotalInventaire(val total: Double?, private val isCategory:Boolean = false){
    val uiLabelRessourceId:Int
        get() {
            return if (isCategory){
                R.string.prompt_categ
            } else {
                R.string.prompt_inv_total
            }
        }
}

@Entity(tableName = "Produits")
class Produit(_id: Int?, nom: String, categ: String, prix: Double, qte: Int) {

    @Ignore
    constructor(nom: String, categ: String, prix: Double, qte: Int):
            this(null,nom,categ, prix, qte)


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Réf produit")
    @NonNull
    val _id: Int? = _id
        get() = field

    @ColumnInfo(name = "nom") var nom: String = nom
        get() = field
        set(value) {
            field = value
        }

    @ColumnInfo(name = "categ") var categ: String = categ
        get() = field
        set(value) {
            field = value
        }
    @ColumnInfo(name = "prix") var prix: Double = prix
        get() = field
        set(value) {
            field = value
        }
    @ColumnInfo(name = "qte") var qte: Int = qte
        get() = field
        set(value) {
            field = value
        }

    val total:Double
        get() = prix * qte

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Produit) return false
        if(
            this._id == other._id &&
            this.nom == other.nom &&
            this.prix == other.prix &&
            this.qte == other.qte &&
            this.categ == other.categ
        ) return true
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Produit(_id=$_id,nom=$nom,categ=$categ,prix=$prix,qte=$qte)"
    }

    companion object {
        val PRODUIT_COMPARATOR = object : DiffUtil.ItemCallback<Produit>() {
            override fun areItemsTheSame(oldItem: Produit, newItem: Produit): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Produit, newItem: Produit): Boolean =
                oldItem == newItem
        }
    }
}