package com.ceolin.heroes_list.ui.heroeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ceolin.core.extensions.collectWithLifecycle
import com.ceolin.core.extensions.gone
import com.ceolin.core.extensions.show
import com.ceolin.domain.network.extensions.showApiError
import com.ceolin.heroes_list.databinding.FragmentHeroesListBinding
import com.ceolin.heroes_list.ui.heroeslist.adapter.HeroesListAdapter
import com.ceolin.heroes_list.viewmodel.HeroesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesListFragment : Fragment() {

    private val viewModel by viewModel<HeroesListViewModel>()

    private lateinit var binding: FragmentHeroesListBinding

    private lateinit var heroesListAdapter: HeroesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupView()
        handleState()
    }

    private fun setupView() {
        binding.recyclerViewHeroes.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = heroesListAdapter
        }
    }

    private fun handleState() {
        viewModel.viewState.collectWithLifecycle(viewLifecycleOwner) {
            when (it) {
                is HeroesListViewState.Initial -> {}
                is HeroesListViewState.HeroesList -> showHeroesList(it)
            }
        }
    }

    private fun showHeroesList(heroesListState: HeroesListViewState.HeroesList) {
        when {
            heroesListState.isLoading -> {
                binding.btnTryAgain.gone()
                showLoading()
            }
            heroesListState.isError -> {
                hideLoading()
                showApiError(heroesListState.throwable)
                binding.btnTryAgain.show()
            }
            !heroesListState.heroesList.isNullOrEmpty() -> {
                heroesListAdapter.heroes = heroesListState.heroesList
                binding.recyclerViewHeroes.show()
                hideLoading()
            }
        }
    }

    private fun setListeners() {
        heroesListAdapter = HeroesListAdapter { hero ->
            hero?.let {
                findNavController().navigate(
                    HeroesListFragmentDirections.actionHeroesListFragmentToHeroDetailsFragment(
                        hero
                    )
                )
            }
        }

        binding.btnTryAgain.setOnClickListener {
            viewModel.getHeroesList()
        }
    }

    private fun showLoading() {
        binding.progress.show()
    }

    private fun hideLoading() {
        binding.progress.gone()
    }

}