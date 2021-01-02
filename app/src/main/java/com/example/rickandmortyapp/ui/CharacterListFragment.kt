package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.adapter.CharacterPagingAdapter
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.listeners.CharacterSelectionListener
import com.example.rickandmortyapp.viewmodels.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@AndroidEntryPoint
class CharacterListFragment : Fragment(), CharacterSelectionListener {

    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharacterListBinding

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.characterList) {
            setHasFixedSize(true)
            adapter = CharacterPagingAdapter(this@CharacterListFragment)
            addItemDecoration(dividerItemDecoration)
        }

        getCharacters()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val linearMenuItem = menu.findItem(R.id.linear_list_menu_item)
        val gridMenuItem = menu.findItem(R.id.grid_list_menu_item)

        val (gridState, linearState) = when (binding.characterList.layoutManager) {
            is GridLayoutManager -> false to true
            else -> true to false
        }

        gridMenuItem.isVisible = gridState
        linearMenuItem.isVisible = linearState
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.character_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.characterList.layoutManager = when (item.itemId) {
            R.id.linear_list_menu_item -> {
                linearLayoutManager
            }
            else -> {
                gridLayoutManager
            }
        }

        activity?.invalidateOptionsMenu()
        return super.onOptionsItemSelected(item)
    }

    //region Character Change Listener Methods

    override fun onCharacterSelected(characterEntity: CharacterEntity) {
        findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(characterEntity))
    }

    override fun onCharacterFavoriteChanged(isChecked: Boolean, characterEntity: CharacterEntity) {

    }

//endregion

    private fun getCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.collect { pagingData -> (binding.characterList.adapter as? CharacterPagingAdapter)?.submitData(pagingData) }
        }
    }
}