package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field) extends Observable:

  def put(stone: Stone, x: Int, y: Int) = field.put(stone, x, y)

  def setBombs(bombNumber: Int) = field.setBombs(bombNumber)

  def revealValue(x: Int, y: Int) = field.revealValue(x, y)

  def createFieldWithBombs =
    if (field.matrix.rowNum == 8) then field = setBombs(10)
    else if (field.matrix.rowNum == 16) then field = setBombs(40)
    else if (field.matrix.rowNum == 32) then field = setBombs(99)

  notifyObservers

  override def toString = field.toString
