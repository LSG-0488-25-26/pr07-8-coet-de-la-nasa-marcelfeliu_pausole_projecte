package com.example.marcelfeliu_pausole_projecte.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marcelfeliu_pausole_projecte.R
import com.example.marcelfeliu_pausole_projecte.model.Character
import com.example.marcelfeliu_pausole_projecte.model.DBCharacter
import com.example.marcelfeliu_pausole_projecte.model.Data
import com.example.marcelfeliu_pausole_projecte.room.RoomRepository
import com.example.retrofitapp.api.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RickAndMortyViewModel : ViewModel() {

    //API
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _charactersData = MutableLiveData<Data>()
    val charactersData = _charactersData

    //ROOM
    private val dbRepository = RoomRepository()

    private val _isCaptured = MutableLiveData<Boolean>(false)
    val isCaptured: MutableLiveData<Boolean> = _isCaptured

    private val _captured = MutableLiveData<MutableList<DBCharacter>>()
    val captured = _captured

    private val _allCharacters = MutableLiveData<MutableList<DBCharacter>>()
    val allCharacters = _allCharacters

    private val _isCapturingCharacter = MutableLiveData<Boolean>(false)
    val isCapturingCharacter: MutableLiveData<Boolean> = _isCapturingCharacter

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

    fun getCaptured(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = dbRepository.getCaptured()
            withContext(Dispatchers.Main){
                _captured.value = response
                _loading.value = false
            }
        }
    }

    fun isCaptured(char: Character){
        CoroutineScope(Dispatchers.IO).launch {
            val response = dbRepository.isCaptured(char.name)
            withContext(Dispatchers.Main){
                _isCaptured.value = response
            }
        }
    }

    fun captureCharacter(char: Character, onComplete: () -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            dbRepository.captureCharacter(char)
            getCaptured()
            withContext(Dispatchers.Main) {
                _isCaptured.value = true
                onComplete()
            }
        }
    }

    fun freeCharacter(char: Character, onComplete: () -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            dbRepository.freeCharacter(char)
            getCaptured()
            withContext(Dispatchers.Main){
                _isCaptured.value = false
                onComplete()
            }
        }
    }

    fun toggleIsCapturingCharacter(){
        this._isCapturingCharacter.value = this._isCapturingCharacter.value!!.not()
    }
}