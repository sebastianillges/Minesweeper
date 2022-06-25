package de.htwg.se.minesweeper.model.FileIOComponent

import de.htwg.se.minesweeper.model.FieldComponent.*

trait FileIOInterface:
  def load: FieldInterface
  def save(grid: FieldInterface): Unit
