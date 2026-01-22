package com.example.marcelfeliu_pausole_projecte.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marcelfeliu_pausole_projecte.R
import com.example.marcelfeliu_pausole_projecte.model.Character
import com.example.marcelfeliu_pausole_projecte.model.Data
import com.example.retrofitapp.api.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RickAndMortyViewModel : ViewModel() {

    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _charactersData = MutableLiveData<Data>()
    val charactersData = _charactersData

    fun getCharacters() {

        CoroutineScope(Dispatchers.IO).launch {

            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _charactersData.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }
    private val _currentCharacter = MutableLiveData<Character>()
    var currentCharacter= _currentCharacter

    fun setCurrentCharacter(character: Character){
        _currentCharacter.value = character
    }
}