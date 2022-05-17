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

  def calculateBombAmount(field: Field): Int =
    field.calculateBombAmount(field)

  def setBombs(bombAmount: Int): Field =
    field = field.setBombs(bombAmount)
    notifyObservers
    field

  def setFlag(coordinates: Coordinates) =
    field.setFlag(coordinates.x, coordinates.y)

  override def toString = field.toString
