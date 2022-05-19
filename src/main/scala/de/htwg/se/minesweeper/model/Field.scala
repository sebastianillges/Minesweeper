package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.util.{ReplaceStrategy, SetBombsTemplate, SetBomb}

import scala.collection.mutable
import scala.util.Random as r

case class Field(matrix: Matrix[Stone, Stone]):
  def this(rows: Int = 3, cols: Int = 3, filling: (Stone, Stone) = (Stone.NotTracked, Stone.EmptyTracked)) =
    this(new Matrix[Stone, Stone](rows, cols, filling))

  val cols: Int = matrix.colNum
  val rows: Int = matrix.rowNum
  val eol: String = sys.props("line.separator")

  def firstBar(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "┌" + (("─" * (cellWidth - 1)) + "┬") * (row - 1) + ("─" * (cellWidth - 1)) + "┐" + eol
    } else {
      "┌" + (("─" * cellWidth) + "┬") * (row - 1) + ("─" * cellWidth) + "┐" + eol
    }

  def bar(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "├" + (("─" * (cellWidth - 1)) + "┼") * (row - 1) + ("─" * (cellWidth - 1)) + "┤" + eol
    } else {
      "├" + (("─" * cellWidth) + "┼") * (row - 1) + ("─" * cellWidth) + "┤" + eol
    }

  def lastBar(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "└" + (("─" * (cellWidth - 1)) + "┴") * (row - 1) + ("─" * (cellWidth - 1)) + "┘" + eol
    } else {
      "└" + (("─" * cellWidth) + "┴") * (row - 1) + ("─" * cellWidth) + "┘" + eol
    }

  def cells(row: Int, cellWidth: Int = 3): String =
    matrix
      .row(row)
      .map(_._1)
      .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
      .mkString("│", "│", "│") + eol

  def matchfield(cellWidth: Int = 3): String =
    (0 until rows)
      .map(cells(_, cellWidth))
      .mkString(firstBar(cellWidth, cols), bar(cellWidth, cols), lastBar(cellWidth, cols))

  override def toString: String = matchfield()

  def getCell(x: Int, y: Int): (Stone, Stone) = matrix.cell(x, y)

  def setBombs(bombNumber: Int): Field =
    val resField = new SetBomb()
    resField.setBombs(bombNumber, this)

  def revealValue(x: Int, y: Int): Field =
    if (!this.getCell(x, y)._1.equals(Stone.NotTracked)) then this
    else copy(this.matrix.replaceCell(x, y, (this.getCell(x, y)._2, this.getCell(x, y)._1)))

  def setFlag(x: Int, y: Int): Field =
    var resultField = this
    if (detectBombAmount(detectBombs(this)) == detectFlags(this).size) then
      if (getCell(x, y)._1 == Stone.Flag) then ReplaceStrategy.strategy(true, this, x, y, Stone.NotTracked)
      else resultField
    else if (getCell(x, y)._1 == Stone.Flag) then ReplaceStrategy.strategy(true, this, x, y, Stone.NotTracked)
    else if (getCell(x, y)._1 == Stone.NotTracked) then ReplaceStrategy.strategy(true, this, x, y, Stone.Flag)
    else resultField

  def detectBombs(field: Field): Map[Coordinates, Boolean] =
    var bombMap: Map[Coordinates, Boolean] = Map.empty[Coordinates, Boolean]
    for (i <- (0 until field.rows))
      for (j <- (0 until field.cols))
        if (field.getCell(i, j)._2 == Stone.Bomb) then bombMap = bombMap + (new Coordinates(i, j) -> true)
        else bombMap = bombMap + (new Coordinates(i, j) -> false)
    bombMap

  def detectBombAmount(map: Map[Coordinates, Boolean]): Int =
    var count = 0
    var result = map.foreach(x => if (x._2) then count = count + 1)
    count

  def detectFlags(field: Field): Map[Coordinates, Boolean] =
    var flagMap: Map[Coordinates, Boolean] = Map.empty[Coordinates, Boolean]
    for (i <- (0 until field.rows))
      for (j <- (0 until field.cols))
        if (field.getCell(i, j)._1 == Stone.Flag) then flagMap = flagMap + (new Coordinates(i, j) -> true)
    flagMap

  def calculateBombAmount(field: Field): Int =
    Math.round((field.rows * field.cols).floatValue() * 0.164.floatValue())
