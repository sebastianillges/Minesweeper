package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.util.{ReplaceStrategy, RevealStrategy}

import scala.collection.mutable
import scala.language.postfixOps
import scala.util.Random as r

case class Field(matrix: Matrix[Stone, Stone, Int]):
  def this(rows: Int = 3, cols: Int = 3, filling: (Stone, Stone, Int) = (Stone.NotTracked, Stone.EmptyTracked, 0)) =
    this(new Matrix[Stone, Stone, Int](rows, cols, filling))

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
      .mkString("│", "│", "│")

  def matchfield(cellWidth: Int = 3): String =
    (" " * (cellWidth + 1))
      .+(
        (0 until cols)
          .map(x => if (x < 10) then x.toString + (" ") * cellWidth else x.toString + (" ") * ((cellWidth - 1)))
          .mkString
      )
      .+("\n")
      .+(
        (0 until rows)
          .map(x =>
            if (x < 10) then x.toString + " " + cells(x, cellWidth) + " " + x.toString + "\n"
            else x.toString + cells(x, cellWidth) + " " + x.toString + "\n"
          )
          .mkString(
            "  " + firstBar(cellWidth, cols),
            "  " + bar(cellWidth, cols),
            "  " + lastBar(cellWidth, cols)
          )
      )
      .+(
        (" " * (cellWidth + 1))
          .+(
            (0 until cols)
              .map(x => if (x < 10) then x.toString + (" ") * cellWidth else x.toString + (" ") * ((cellWidth - 1)))
              .mkString
          )
      )

  override def toString: String = matchfield()

  def getCell(x: Int, y: Int): (Stone, Stone, Int) = matrix.cell(x, y)

  def setBombs(bombNumber: Int): Field =
    setBombsR(bombNumber, this)

  def setBombsR(bombNumber: Int, field: Field, count: Int = 0): Field =
    var row = r.nextInt(field.rows)
    val col = r.nextInt(field.cols)
    if (count == bombNumber) then field
    else if (field.matrix.row(row)(col)._2.equals(Stone.Bomb)) then setBombsR(bombNumber, field, count)
    else
      val resField = ReplaceStrategy.strategy(false, field, row, col, Stone.Bomb)
      val countR = count + 1
      setBombsR(bombNumber, resField, countR)

  def revealValue(x: Int, y: Int): Field =
    RevealStrategy.strategy(x, y, this)

  def setFlag(x: Int, y: Int): Field =
    var resultField = this
    if (detectBombs(this).size == detectFlags(this).size) then
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
    bombMap

  def detectFlags(field: Field): Map[Coordinates, Boolean] =
    var flagMap: Map[Coordinates, Boolean] = Map.empty[Coordinates, Boolean]
    for (i <- (0 until field.rows))
      for (j <- (0 until field.cols))
        if (field.getCell(i, j)._1 == Stone.Flag) then flagMap = flagMap + (new Coordinates(i, j) -> true)
    flagMap

  def calculateBombAmount(field: Field): Int =
    Math.round((field.rows * field.cols).floatValue() * 0.164.floatValue())

  def putValues(field: Field): Field =
    var res = field
    field
      .detectBombs(field)
      .foreach(a =>
        val x = a._1.x
        val y = a._1.y
        for (i <- (-1 until (2)))
          for (j <- (-1 until (2)))
            val m = x + i
            val n = y + j
            if (m > -1 && m < res.rows && n > -1 && n < res.cols) then
              res = res.copy(
                res.matrix.replaceCell(m, n, (res.getCell(m, n)._1, res.getCell(m, n)._2, res.matrix.row(m)(n)._3 + 1))
              )
      )
    res

  def showValues(field: Field): Field =
    var res = field
    res = res.putValues(res)
    for (i <- (0 until res.rows))
      for (j <- (0 until res.cols))
        if (res.matrix.row(i)(j)._2.equals(Stone.Bomb)) then res
        else if (res.matrix.row(i)(j)._3 == 1) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.One)
        else if (res.matrix.row(i)(j)._3 == 2) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Two)
        else if (res.matrix.row(i)(j)._3 == 3) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Three)
        else if (res.matrix.row(i)(j)._3 == 4) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Four)
        else if (res.matrix.row(i)(j)._3 == 5) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Five)
        else if (res.matrix.row(i)(j)._3 == 6) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Six)
        else if (res.matrix.row(i)(j)._3 == 7) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Seven)
        else if (res.matrix.row(i)(j)._3 == 8) then res = ReplaceStrategy.strategy(false, res, i, j, Stone.Eight)
    res
