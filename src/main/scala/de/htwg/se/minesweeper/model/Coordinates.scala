package de.htwg.se.minesweeper.model

case class Coordinates(x: Int, y: Int, flag: Char):
  def this(x: Int, y: Int) = this(x, y, ' ')
