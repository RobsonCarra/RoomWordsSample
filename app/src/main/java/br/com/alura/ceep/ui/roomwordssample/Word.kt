package br.com.alura.ceep.ui.roomwordssample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
  @PrimaryKey val word: String,
  val size: Int,
  val description: String,
  val price: Double
)
