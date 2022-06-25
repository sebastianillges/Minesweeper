package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*
import de.htwg.se.minesweeper.util.*

import scala.collection.mutable.ListBuffer

class DoCommand(coordinates: Coordinates) extends Command[FieldInterface] {

  var fieldUndo: Option[FieldInterface] = None
  var fieldRedo: Option[FieldInterface] = None

  override def noStep(field: FieldInterface): FieldInterface = field

  override def doStep(field: FieldInterface): FieldInterface =
    fieldUndo = Some(field)
    field.revealValue(coordinates.x, coordinates.y)

  override def doFlag(field: FieldInterface): FieldInterface =
    fieldUndo = Some(field)
    field.setFlag(coordinates.x, coordinates.y)

  override def redoStep(field: FieldInterface): FieldInterface =
    fieldUndo = Some(field)
    fieldRedo.get

  override def undoStep(field: FieldInterface): FieldInterface =
    fieldRedo = Some(field)
    fieldUndo.get
}
