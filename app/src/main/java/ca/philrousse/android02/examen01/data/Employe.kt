package ca.philrousse.android02.examen01.data

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Employes")
data class Employes (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val _id:Int?,
    val nom:String,
    val prenom:String,
    val sexe:String,
    val titre:String,
    val salaire:Int
        ){
    companion object {
        val EMPLOYES_COMPARATOR = object : DiffUtil.ItemCallback<Employes>() {
            override fun areItemsTheSame(oldItem: Employes, newItem: Employes): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Employes, newItem: Employes): Boolean =
                oldItem == newItem
        }
    }
}