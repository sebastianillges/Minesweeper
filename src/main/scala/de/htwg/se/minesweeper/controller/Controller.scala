package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Coordinates
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.{Event, Observable, UndoManager}

case class Controller(var field: Field) extends Observable :
  val undoManager = new UndoManager[Field]

  def doAndPublish(doThis: Coordinates => Field, coordinates: Coordinates): Unit =
    field = doThis(coordinates)
    notifyObservers(Event.Move)

  def doAndPublish(doThis: => Field): Unit =
    field = doThis
    notifyObservers(Event.Move)

  def quit(): Unit = notifyObservers(Event.Quit)

  def getCell(coordinates: Coordinates): (Stone, Stone, Int) = field.getCell(coordinates.x, coordinates.y)

  def revealValue(move: Coordinates): Field =
    if field.getCell(move.x, move.y)._1 != Stone.NotTracked then undoManager.noStep(field, DoCommand(move))
    else undoManager.doStep(field, DoCommand(move))

  def undo: Field = undoManager.undoStep(field)

  def redo: Field = undoManager.redoStep(field)

  def noStep(move: Coordinates): Field = undoManager.noStep(field, DoCommand(move))

  def calculateBombAmount(field: Field): Int =
    field.calculateBombAmount(field)

  def setBombs(bombAmount: Int): Field =
    field = field.setBombs(bombAmount)
    field = field.showValues(field)
    notifyObservers(Event.Move)
    field

  def setFlag(coordinates: Coordinates): Field =
    undoManager.doFlag(field, DoCommand(coordinates))

  override def toString: String = field.toString
