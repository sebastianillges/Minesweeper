package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.*

import scala.collection.mutable.ListBuffer

class DoCommand(coordinates: Coordinates) extends Command[Field] {

  var fieldUndo: Option[Field] = None
  var fieldRedo: Option[Field] = None

  override def noStep(field: Field): Field = field

  override def doStep(field: Field): Field =
    fieldUndo = Some(field)
    field.revealValue(coordinates.x, coordinates.y)

  override def doFlag(field: Field): Field =
    fieldUndo = Some(field)
    field.setFlag(coordinates.x, coordinates.y)

  override def redoStep(field: Field): Field =
    fieldUndo = Some(field)
    fieldRedo.get

  override def undoStep(field: Field): Field =
    fieldRedo = Some(field)
    fieldUndo.get
}
