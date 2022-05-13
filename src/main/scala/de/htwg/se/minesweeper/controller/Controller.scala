package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Coordinates
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.Observable

case class Controller(var field: Field) extends Observable:

  def doAndPublish(doThis: Coordinates => Field, coordinates: Coordinates) =
    field = doThis(coordinates)
    notifyObservers

  def getCell(coordinates: Coordinates) = field.getCell(coordinates.x, coordinates.y)

  def revealValue(move: Coordinates) =
    field.revealValue(move.x, move.y)

  def createFieldWithBombs(): Field =
    if (field.rows == 8) then
      field = field.setBombs(10)
      notifyObservers
      field
    else if (field.rows == 16) then
      field = field.setBombs(40)
      notifyObservers
      field
    else if (field.rows == 32) then
      field = field.setBombs(99)
      notifyObservers
      field
    else
      notifyObservers
      field

  def setFlag(coordinates: Coordinates) =
    field.setFlag(coordinates.x, coordinates.y)

  override def toString = field.toString
