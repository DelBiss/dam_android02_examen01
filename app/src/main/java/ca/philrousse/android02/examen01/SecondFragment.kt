package ca.philrousse.android02.examen01

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ca.philrousse.android02.examen01.data.Employes
import ca.philrousse.android02.examen01.data.Produit
import ca.philrousse.android02.examen01.databinding.FragmentSecondBinding
import ca.philrousse.android02.examen01.viewModel.EmployesViewModel
import ca.philrousse.android02.examen01.viewModel.EmployesViewModelFactory
import com.google.android.material.textfield.TextInputLayout

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: EmployesViewModel by activityViewModels {
        EmployesViewModelFactory((activity!!.application as EmployesApplication).repository)
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                save()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun validateIsNotNull(til: TextInputLayout):Boolean{
        if(getInputString(til).isEmpty()){
            til.error = getString(R.string.error_field_required_empty)
            return false
        }
        til.error = null
        return true
    }

    private fun validate():Boolean{
        var isValid = true
        isValid = validateIsNotNull(binding.employeeNom) && isValid
        isValid = validateIsNotNull(binding.employeePrenom) && isValid
        isValid = validateIsNotNull(binding.employeeTitre) && isValid
        isValid = validateIsNotNull(binding.employeeSalaire) && isValid
        return isValid
    }

    private fun getInputString(til: TextInputLayout):String{
        return til.editText?.text.toString()
    }

    private fun getSexe():String{
        if(binding.employeeSexeMasculin.isChecked){
            return "M"
        }

        return "F"
    }
    private fun getEmployee(): Employes?{
        if(validate()) {
            return Employes( null,
                getInputString(binding.employeeNom),
                getInputString(binding.employeePrenom),
                getSexe(),
                getInputString(binding.employeeTitre),
                getInputString(binding.employeeSalaire).toInt()
            )
        }
        return null
    }

    private fun save(){
        if(validate()){
            getEmployee()?.let { newProduct ->
                viewModel.insert(newProduct)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}