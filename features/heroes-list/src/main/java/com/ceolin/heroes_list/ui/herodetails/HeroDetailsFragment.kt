package com.ceolin.heroes_list.ui.herodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ceolin.core.extensions.show
import com.ceolin.heroes_list.R
import com.ceolin.heroes_list.databinding.FragmentHeroDetailsBinding
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.squareup.picasso.Picasso
import com.ceolin.design.R.drawable as Design

class HeroDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailsBinding
    private val args: HeroDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        val heroDetails = args.heroDetails
        populateHeroDetailsFields(heroDetails)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.setHomeAsUpIndicator(Design.ic_navigation_icon_white)
        (activity as AppCompatActivity?)?.supportActionBar?.elevation = 0F
    }

    private fun populateHeroDetailsFields(heroDetails: HeroesResponseItem) {
        with(heroDetails) {
            with(binding) {
                Picasso.get().load(heroDetails.img).into(heroImage)

                if (isTextAvailable(name)) setTextAndVisible(
                    heroName,
                    getString(R.string.name, name)
                )
                if (isTextAvailable(flavor)) setTextAndVisible(
                    heroDescriptionFlavor,
                    getString(R.string.description_flavor, flavor)
                )
                if (isTextAvailable(text)) setTextAndVisible(
                    heroShortDescription,
                    getString(R.string.description_short, text)
                )
                if (isTextAvailable(cardSet)) setTextAndVisible(
                    heroSet,
                    getString(R.string.set, cardSet)
                )
                if (isTextAvailable(type)) setTextAndVisible(
                    heroType,
                    getString(R.string.type, type)
                )
                if (isTextAvailable(faction)) setTextAndVisible(
                    heroFaction,
                    getString(R.string.faction, faction)
                )
                if (isTextAvailable(rarity)) setTextAndVisible(
                    heroRare,
                    getString(R.string.rarity, rarity)
                )
                if (isTextAvailable(attack.toString())) setTextAndVisible(
                    heroAttack,
                    getString(R.string.attack, attack.toString())
                )
                if (isTextAvailable(cost.toString())) setTextAndVisible(
                    heroCost,
                    getString(R.string.cost, cost.toString())
                )
                if (isTextAvailable(health.toString())) setTextAndVisible(
                    heroHealth,
                    getString(R.string.health, health.toString())
                )
            }
        }
    }

    private fun setTextAndVisible(textView: TextView, text: String) {
        textView.text = text
        textView.show()
    }

    private fun isTextAvailable(text: String?): Boolean {
        return !text.isNullOrEmpty()
    }
}