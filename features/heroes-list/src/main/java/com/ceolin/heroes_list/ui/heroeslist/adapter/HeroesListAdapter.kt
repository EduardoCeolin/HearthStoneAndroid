package com.ceolin.heroes_list.ui.heroeslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceolin.heroes_list.databinding.ItemHeroBinding
import com.ceolin.heroes_list.domain.model.HeroesResponseItem

class HeroesListAdapter(private val listener: (HeroesResponseItem?) -> Unit) :
    RecyclerView.Adapter<HeroesListViewHolder>() {

    var heroes = listOf<HeroesResponseItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesListViewHolder {
        return HeroesListViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesListViewHolder, position: Int) {
        holder.bind(heroes[position], listener)
    }

    override fun getItemCount() = heroes.size

}
