package com.example.marcelfeliu_pausole_projecte.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marcelfeliu_pausole_projecte.R
import com.example.marcelfeliu_pausole_projecte.model.RMCharacter


class RickAndMortyViewModel : ViewModel() {

    private val _characters = MutableLiveData(mutableListOf(
        RMCharacter("Rick Sanchez", "Alive", "Human", "Male", R.drawable.rick),
        RMCharacter("Morty Smith", "Alive", "Human", "Male", R.drawable.morty),
        RMCharacter("Summer Smith", "Alive", "Human", "Female", R.drawable.summer),
        RMCharacter("Beth Smith", "Alive", "Human", "Female", R.drawable.beth),
        RMCharacter("Jerry Smith", "Alive", "Human", "Male", R.drawable.jerry),
        RMCharacter("Abadango Cluster Princess", "Alive", "Alien", "Female", R.drawable.abadango),
        RMCharacter("Abradolf Lincler", "unknown", "Human", "Male", R.drawable.abradolf),
        RMCharacter("Adjudicator Rick", "Dead", "Human", "Male", R.drawable.adjudicator_rick),
    ))

    val characters: LiveData<MutableList<RMCharacter>> = _characters

    private val _currentCharacter = MutableLiveData(RMCharacter("", "", "", "", R.drawable.rick))
    var currentCharacter: LiveData<RMCharacter> = _currentCharacter

    fun setCurrentCharacter(character: RMCharacter){
        _currentCharacter.value = character
    }
}