package com.example.tictokonline.data

import kotlinx.serialization.Serializable


/**
 *  this data class to sent data from client to server for client(player) and where it makes the turn
 */
@Serializable
data class MakeTurn(
    val x :Int ,
    val y:Int
)
