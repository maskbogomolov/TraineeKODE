package com.example.appkode.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appkode.R
import com.example.appkode.databinding.FragmentUsersListBinding
import com.example.appkode.di.appComponent
import com.example.appkode.util.Data
import com.example.appkode.util.NetworkResponse
import dagger.Lazy
import javax.inject.Inject


class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    @Inject
    lateinit var usersViewModelFactory: Lazy<UsersViewModel.Factory>
    private val viewModel: UsersViewModel by viewModels { usersViewModelFactory.get() }
    private val usersAdapter: UsersAdapter by lazy { UsersAdapter(requireActivity(),viewModel) }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf { it.containsKey("getDepartment") }?.apply {
            viewModel.department.value = getString("getDepartment").toString()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUsersListBinding.bind(view)
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
//        viewModel.users.observe(viewLifecycleOwner) { users ->
//            when(users){
//                is NetworkResponse.Success -> usersAdapter.submitList(users.data)
//                //is NetworkResponse.Error -> Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
//            }
//        }
        viewModel.getUserFromDb().observe(viewLifecycleOwner){users ->
            if (users.isNotEmpty()) usersAdapter.submitList(users) else viewModel.getUser()
        }
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = ""
    }
}