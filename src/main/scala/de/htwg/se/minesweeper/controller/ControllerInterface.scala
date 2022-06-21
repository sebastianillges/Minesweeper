package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Coordinates, FieldInterface, Stone}
import de.htwg.se.minesweeper.util.Observable

trait ControllerInterface extends Observable:
  def doAndPublish(doThis: Coordinates => FieldInterface, coordinates: Coordinates): Unit
  def doAndPublish(doThis: => FieldInterface): Unit
  def quit(): Unit
  def calculateBombAmount(): Int
  def revealValue(move: Coordinates): FieldInterface
  def undo: FieldInterface
  def redo: FieldInterface
  def noStep(move: Coordinates): FieldInterface
  def setBombs(bombAmount: Int): FieldInterface
  def setFlag(coordinates: Coordinates): FieldInterface
  def field: FieldInterface
  def save: FieldInterface
  def load: FieldInterface
