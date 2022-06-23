package ca.philrousse.android02.examen01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.philrousse.android02.examen01.R
import ca.philrousse.android02.examen01.data.Employes
import ca.philrousse.android02.examen01.data.Produit
import ca.philrousse.android02.examen01.databinding.EmployeListItemBinding

class EmployeeAdapter :
    ListAdapter<Employes, EmployeeAdapter.EmployeeViewHolder>(Employes.EMPLOYES_COMPARATOR) {

    class EmployeeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var binding: EmployeListItemBinding

        init {
            binding = EmployeListItemBinding.bind(itemView)

        }

        fun bind(item: Employes) {
            binding.employee = item
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employe_list_item, parent, false)
        return EmployeeViewHolder(view)
    }


    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val produit = getItem(position)
        holder.bind(produit)
    }
}

