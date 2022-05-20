package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Coordinates
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.{Observable, UndoManager}

case class Controller(var field: Field) extends Observable:
  val undoManager = new UndoManager[Field]

  def doAndPublish(doThis: Coordinates => Field, coordinates: Coordinates) =
    field = doThis(coordinates)
    notifyObservers

  def doAndPublish(doThis: => Field) =
    field = doThis
    notifyObservers

  def getCell(coordinates: Coordinates) = field.getCell(coordinates.x, coordinates.y)

  def revealValue(move: Coordinates) =
    if (field.getCell(move.x, move.y)._1 != Stone.NotTracked) then undoManager.noStep(field, DoCommand(move))
    else undoManager.doStep(field, DoCommand(move))

  def undo: Field = undoManager.undoStep(field)

  def redo: Field = undoManager.redoStep(field)

  def noStep(move: Coordinates): Field = undoManager.noStep(field, DoCommand(move))

  def calculateBombAmount(field: Field): Int =
    field.calculateBombAmount(field)

  def setBombs(bombAmount: Int): Field =
    field = field.setBombs(bombAmount)
    notifyObservers
    field

  def setFlag(coordinates: Coordinates) =
    undoManager.doFlag(field, DoCommand(coordinates))

  override def toString = field.toString
