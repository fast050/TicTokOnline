package com.example.tictokonline.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictokonline.data.GameState
import com.example.tictokonline.data.MakeTurn
import com.example.tictokonline.data.RealtimeMessagingClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.ConnectException


class TikTacToeViewModel constructor(
    private val client: RealtimeMessagingClient
) :ViewModel(){

    val state = client
        .getGameStateStream()
        .onStart { _isConnecting.value = true }
        .onEach { _isConnecting.value = false }
        .catch {exception ->
            _showConnectionError.value = exception is ConnectException
        }
        .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , GameState())

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting :StateFlow<Boolean> = _isConnecting.asStateFlow()

    private val _showConnectionError = MutableStateFlow(false)
    val showConnectionError :StateFlow<Boolean> = _showConnectionError.asStateFlow()


    fun finishTurn(x:Int , y:Int) {
        if(state.value.field[x][y]!= null || state.value.winningPlayer!= null) // finish the turn , if already do move in that place or someone win
            return

      viewModelScope.launch {
          client.sendAction(
              MakeTurn(x , y)
          )
      }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            client.close()
        }
    }

}