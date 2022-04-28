package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field) extends Observable:

  def setBombs(bombNumber: Int) = field.setBombs(bombNumber)

  def revealValue(x: Int, y: Int) = field.revealValue(x, y)

  def createFieldWithBombs(param: Int) =
    if (param == 8) then field = setBombs(10)
    else if (param == 16) then field = setBombs(40)
    else if (param == 32) then field = setBombs(99)

  notifyObservers

  override def toString = field.toString
