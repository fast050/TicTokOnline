package com.example.tictokonline.data

import kotlinx.coroutines.flow.Flow

interface RealtimeMessagingClient {
    fun getGameStateStream() : Flow<GameState> // this will be update in real time , when every time the server set the game state this will change
    suspend fun sendAction(action: MakeTurn) // this will be send from the client to server
    suspend fun close() // when the client close the game
}