package com.example.marcelfeliu_pausole_projecte.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marcelfeliu_pausole_projecte.R
import com.example.marcelfeliu_pausole_projecte.model.BottomNavigationScreens
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

    // Characters
    private val _currentCharacter = MutableLiveData<Character>()
    var currentCharacter= _currentCharacter

    var listOfChatacters = mutableListOf<Character>()

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

    // SearchBar
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    private val _showSearchBar = MutableLiveData<Boolean>(false)
    val showSearchBar = _showSearchBar


    //Navigations
    private val _bottomNavigationItems = MutableLiveData<List<BottomNavigationScreens>>(
        listOf(
            BottomNavigationScreens.Home,
            BottomNavigationScreens.Captured
        ))

    public val bottomNavigationItems: LiveData<List<BottomNavigationScreens>> = _bottomNavigationItems

    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {

            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _charactersData.value = response.body()
                    listOfChatacters = _charactersData.value.results as MutableList<Character>
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }


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

    fun getCapturedCharacters() {
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val capturedFromDb = dbRepository.getCaptured()
            withContext(Dispatchers.Main) {
                if (listOfChatacters.isNotEmpty()) {
                    val allApiChars = listOfChatacters
                    val capturedNames = capturedFromDb.map { it.name }.toSet()

                    val filteredList = allApiChars.filter { it.name in capturedNames }

                    _charactersData.value.results = filteredList
                    _loading.value = false
                    Log.d("Edmsd", "exiting coroutione of captured")

                }
            }
        }
    }

    // funcions searchBar
    fun onSearchTextChange(text: String) {
        _searchedText.value = text

        val original = listOfChatacters
        val filtered = original.filter { it.name.contains(text, ignoreCase = true) }

        _charactersData.value = _charactersData.value?.copy(
            results = filtered
        )
    }
    fun addToHistory(text: String) {
        if (text.isNotBlank()) {
            val currentHistory = _searchHistory.value.orEmpty() // Obté la llista actual o una llista buida
            this._searchHistory.value = listOf(text) + currentHistory // Afegeix el nou text al principi
            this._searchedText.value = "" // Neteja el text després de fer la cerca
        }
    }
    fun clearHistory() {
        this._searchHistory.value = emptyList()
    }

    fun toggleSearchBar()
    {
        _showSearchBar.value = !_showSearchBar.value
    }
}