package de.htwg.se.minesweeper.model

import scala.language.postfixOps

case class Field(matrix: Matrix[Stone]):
  def this(rows: Int = 3, cols: Int = 3, filling: Stone = Stone.NotTracked) =
    this(new Matrix[Stone](rows, cols, filling))

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
      .map(_.toString)
      .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
      .mkString("│", "│", "│") + eol

  def matchfield(cellWidth: Int = 3): String =
    (0 until rows)
      .map(cells(_, cellWidth))
      .mkString(firstBar(cellWidth, cols), bar(cellWidth, cols), lastBar(cellWidth, cols))

  override def toString: String = matchfield()
  def put(stone: Stone, x: Int, y: Int): Field = copy(matrix.replaceCell(x, y, stone))
