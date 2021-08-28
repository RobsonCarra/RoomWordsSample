package br.com.alura.ceep.ui.roomwordssample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var email: String,
    var password: String,
    var status: Boolean = true
)