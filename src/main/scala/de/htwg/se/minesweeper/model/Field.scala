package de.htwg.se.minesweeper.model

import scala.language.postfixOps

case class Field(matrix: Matrix[Stone]):
  def this(rows: Int = 3, cols: Int = 3, filling: Stone = Stone.Flag) = this(new Matrix[Stone](rows, cols, filling))

  val cols: Int = matrix.colNum
  val rows: Int = matrix.rowNum
  val eol: String = sys.props("line.separator")

  def firstHorizontal(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "┌" + (("─" * (cellWidth - 1)) + "┬") * (row - 1) + ("─" * (cellWidth - 1)) + "┐" + eol
    } else {
      "┌" + (("─" * cellWidth) + "┬") * (row - 1) + ("─" * cellWidth) + "┐" + eol
    }

  def horizontal(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "├" + (("─" * (cellWidth - 1)) + "┼") * (row - 1) + ("─" * (cellWidth - 1)) + "┤" + eol
    } else {
      "├" + (("─" * cellWidth) + "┼") * (row - 1) + ("─" * cellWidth) + "┤" + eol
    }

  def lastHorizontal(cellWidth: Int = 3, row: Int = rows): String =
    if (cellWidth % 2 == 0) {
      "└" + (("─" * (cellWidth - 1)) + "┴") * (row - 1) + ("─" * (cellWidth - 1)) + "┘" + eol
    } else {
      "└" + (("─" * cellWidth) + "┴") * (row - 1) + ("─" * cellWidth) + "┘" + eol
    }

  def vertical(row: Int = rows, cellWidth: Int = 3): String =
    if (row == 1) {
      matrix
        .row(row - 1)
        .map(_.toString)
        .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
        .mkString("│", "│", "│") + eol
    } else {
      matrix
        .row(row)
        .map(_.toString)
        .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
        .mkString("│", "│", "│") + eol
    }

  def matchfield(cellWidth: Int = 3): String =
    (0 until rows)
      .map(vertical(_, cellWidth))
      .mkString(firstHorizontal(cellWidth, cols), horizontal(cellWidth, cols), lastHorizontal(cellWidth, cols))

  override def toString: String = matchfield()
  def put(stone: Stone, x: Int, y: Int): Field = copy(matrix.replaceCell(x, y, stone))
