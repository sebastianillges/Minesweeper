package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.minesweeper.controller.controllerComponent.*
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*
import de.htwg.se.minesweeper.model.FileIOComponent.*
import de.htwg.se.minesweeper.util.{DifficultyFactory, Event, Observable, UndoManager}
import de.htwg.se.minesweeper.{MinesweeperJson, MinesweeperXML}

case class Controller @Inject() (var field: FieldInterface) extends ControllerInterface with Observable:
  val undoManager = new UndoManager[FieldInterface]
  val file = Guice.createInjector(new MinesweeperJson)
  val fileIO = file.getInstance(classOf[FileIOInterface])

  def createNewField(string: String): FieldInterface =
    string match {
      case "1" =>
        field = DifficultyFactory.apply("1").run
        setBombs(calculateBombAmount())
      case "2" =>
        field = DifficultyFactory.apply("2").run
        setBombs(calculateBombAmount())
      case "3" =>
        field = DifficultyFactory.apply("3").run
        setBombs(calculateBombAmount())
    }

  def doAndPublish(doThis: Coordinates => FieldInterface, coordinates: Coordinates): Unit =
    field = doThis(coordinates)
    notifyObservers(Event.Move)

  def doAndPublish(doThis: => FieldInterface): Unit =
    field = doThis
    notifyObservers(Event.Move)

  def quit(): Unit = notifyObservers(Event.Quit)

  def revealValue(move: Coordinates): FieldInterface =
    if field.getCell(move.x, move.y)._1 != Stone.NotTracked then undoManager.noStep(field, DoCommand(move))
    else undoManager.doStep(field, DoCommand(move))

  def undo: FieldInterface =
    undoManager.undoStep(field)

  def redo: FieldInterface =
    undoManager.redoStep(field)

  def noStep(move: Coordinates): FieldInterface =
    undoManager.noStep(field, DoCommand(move))

  def calculateBombAmount(): Int =
    field.calculateBombAmount()

  def setBombs(bombAmount: Int): FieldInterface =
    field = field.setBombs(bombAmount)
    field = field.showValues()
    notifyObservers(Event.Move)
    field

  def setFlag(coordinates: Coordinates): FieldInterface =
    undoManager.doFlag(field, DoCommand(coordinates))

  def save: FieldInterface =
    fileIO.save(field)
    field

  def load: FieldInterface =
    field = fileIO.load
    field

  def flagsLeft(): Int =
    field.flagsLeft()

  override def toString: String = field.toString
