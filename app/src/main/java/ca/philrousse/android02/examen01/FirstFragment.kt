package ca.philrousse.android02.examen01

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ca.philrousse.android02.examen01.adapter.EmployeeAdapter
import ca.philrousse.android02.examen01.data.Employes
import ca.philrousse.android02.examen01.data.Produit
import ca.philrousse.android02.examen01.viewModel.EmployesViewModel
import ca.philrousse.android02.examen01.viewModel.EmployesViewModelFactory
import ca.philrousse.android02.examen01.databinding.FragmentFirstBinding
import ca.philrousse.android02.examen01.generic.ListSwipeDeleteGesture
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: EmployesViewModel by activityViewModels {
        EmployesViewModelFactory((activity!!.application as EmployesApplication).repository)
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hookRecycleView()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
        val searchView = menu.findItem(R.id.action_filter).actionView as SearchView
        searchView.setOnQueryTextListener(viewModel)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_save -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hookRecycleView(){
        val recyclerView: RecyclerView = binding.recyclerView
        val adapter = EmployeeAdapter()

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayout.VERTICAL
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
        recyclerView.adapter = adapter

        val listeSwipeTouchHelper = ListSwipeDeleteGesture(
            icon = resources.getDrawable(R.drawable.ic_delete, null),
            color = Color.RED,
        ) {
            viewModel.delete(adapter.currentList[it])
        }

        ItemTouchHelper(listeSwipeTouchHelper).attachToRecyclerView(recyclerView)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.get().collect {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it as MutableList<Employes>)
                    } else {
                        adapter.submitList(null)
                    }
                }
            }
        }
    }
}