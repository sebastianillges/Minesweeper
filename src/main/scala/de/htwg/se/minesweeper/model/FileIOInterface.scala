package de.htwg.se.minesweeper.model

trait FileIOInterface:
  def load: FieldInterface
  def save(grid: FieldInterface): Unit
