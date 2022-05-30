package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.*

import scala.collection.mutable.ListBuffer

class DoCommand(coordinates: Coordinates) extends Command[IField] {

  var fieldUndo: Option[IField] = None
  var fieldRedo: Option[IField] = None

  override def noStep(field: IField): IField = field

  override def doStep(field: IField): IField =
    fieldUndo = Some(field)
    field.revealValue(coordinates.x, coordinates.y)

  override def doFlag(field: IField): IField =
    fieldUndo = Some(field)
    field.setFlag(coordinates.x, coordinates.y)

  override def redoStep(field: IField): IField =
    fieldUndo = Some(field)
    fieldRedo.get

  override def undoStep(field: IField): IField =
    fieldRedo = Some(field)
    fieldUndo.get
}
