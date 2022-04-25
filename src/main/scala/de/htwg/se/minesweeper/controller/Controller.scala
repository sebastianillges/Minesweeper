package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field) extends Observable:

  def put(stone: Stone, x: Int, y: Int) = field.put(stone, x, y)

  def setBombs(bombNumber: Int) = field.setBombs(bombNumber)

  def revealValue(x: Int, y: Int) = field.revealValue(x, y)

  notifyObservers

  override def toString = field.toString
