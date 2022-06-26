package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*

trait FieldInterface:
  val cols: Int
  val rows: Int
  def firstBar(cellWidth: Int, row: Int): String
  def bar(cellWidth: Int, row: Int): String
  def lastBar(cellWidth: Int, row: Int): String
  def cells(row: Int, cellWidth: Int): String
  def matchfield(cellWidth: Int): String
  def getCell(x: Int, y: Int): (Stone, Stone, Int)
  def setCell(x: Int, y: Int, cell: (Stone, Stone, Int)): FieldInterface
  def setBombs(bombNumber: Int): FieldInterface
  def revealValue(x: Int, y: Int): FieldInterface
  def setFlag(x: Int, y: Int): FieldInterface
  def detectBombs(): Map[Coordinates, Boolean]
  def detectFlags(): Map[Coordinates, Boolean]
  def calculateBombAmount(): Int
  def putValues(): FieldInterface
  def showValues(): FieldInterface
  def matrix: Matrix[Stone, Stone, Int]
  def flagsLeft(): Int
  def toStone(string: String): Stone
