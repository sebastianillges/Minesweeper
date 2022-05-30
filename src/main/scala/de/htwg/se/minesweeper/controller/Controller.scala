package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Coordinates
import de.htwg.se.minesweeper.model.IField
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.model.Stone
import de.htwg.se.minesweeper.util.{Event, Observable, UndoManager}

case class Controller(var field: IField) extends IController with Observable:
  val undoManager = new UndoManager[IField]
  field = new Field(field.rows, field.cols)

  def doAndPublish(doThis: Coordinates => IField, coordinates: Coordinates): Unit =
    field = doThis(coordinates)
    notifyObservers(Event.Move)

  def doAndPublish(doThis: => IField): Unit =
    field = doThis
    notifyObservers(Event.Move)

  def quit(): Unit = notifyObservers(Event.Quit)

  def getCell(coordinates: Coordinates): (Stone, Stone, Int) =
    field.getCell(coordinates.x, coordinates.y)

  def revealValue(move: Coordinates): IField =
    if field.getCell(move.x, move.y)._1 != Stone.NotTracked then undoManager.noStep(field, DoCommand(move))
    else undoManager.doStep(field, DoCommand(move))

  def undo: IField =
    undoManager.undoStep(field)

  def redo: IField =
    undoManager.redoStep(field)

  def noStep(move: Coordinates): IField =
    undoManager.noStep(field, DoCommand(move))

  def calculateBombAmount(): Int =
    field.calculateBombAmount()

  def setBombs(bombAmount: Int): IField =
    field = field.setBombs(bombAmount)
    field = field.showValues()
    notifyObservers(Event.Move)
    field

  def setFlag(coordinates: Coordinates): IField =
    field = new Field(field.rows, field.cols)
    undoManager.doFlag(field, DoCommand(coordinates))

  override def toString: String = field.toString
