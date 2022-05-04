package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Coordinates
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field) extends Observable:

  def doAndPublish(doThis: Coordinates => Field, move: Coordinates) =
    field = doThis(move)
    notifyObservers

  def revealValue(move: Coordinates) =
    field.revealValue(move.x, move.y)

  def createFieldWithBombs =
    if (field.matrix.rowNum == 8) then field = field.setBombs(10)
    else if (field.matrix.rowNum == 16) then field = field.setBombs(40)
    else if (field.matrix.rowNum == 32) then field = field.setBombs(99)
    notifyObservers

  override def toString = field.toString
