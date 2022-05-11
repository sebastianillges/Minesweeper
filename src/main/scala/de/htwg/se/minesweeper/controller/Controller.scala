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

  def createFieldWithBombs =
    if (field.matrix.rowNum == 8) then field = field.setBombs(10)
    else if (field.matrix.rowNum == 16) then field = field.setBombs(40)
    else if (field.matrix.rowNum == 32) then field = field.setBombs(99)
    notifyObservers

  def setFlag(coordinates: Coordinates) =
    field.setFlag(coordinates.x, coordinates.y)

  override def toString = field.toString
