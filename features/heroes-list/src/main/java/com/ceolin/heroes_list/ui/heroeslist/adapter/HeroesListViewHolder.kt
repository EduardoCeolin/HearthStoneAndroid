package com.ceolin.heroes_list.ui.heroeslist.adapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ceolin.heroes_list.R
import com.ceolin.heroes_list.databinding.ItemHeroBinding
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.squareup.picasso.Picasso

class HeroesListViewHolder(binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
    private val heroImage: ImageView = binding.heroImage

    fun bind(hero: HeroesResponseItem?, listener: (HeroesResponseItem?) -> Unit) {
        val circularProgressDrawable = CircularProgressDrawable(heroImage.context).apply {
            strokeWidth = 5f
            centerRadius = 90f
            setColorSchemeColors(
                ContextCompat.getColor(heroImage.context, R.color.black)
            )
            start()
        }

        Picasso.get()
            .load(hero?.img)
            .resize(160, 220)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(heroImage)

        heroImage.setOnClickListener {
            listener(hero)
        }
    }
}

