package de.htwg.se.minesweeper.model

import scala.util.Random as r

case class Field(matrix: Matrix[Stone, Stone]):
  def this(rows: Int = 3, cols: Int = 3, filling: (Stone, Stone)) =
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

  def put(stone: Stone, x: Int, y: Int): Field = copy(matrix.replaceCell(x, y, (this.matrix.row(x)(y)._1, stone)))

  def setBombs(x: Int, y: Int): Field =
    put(Stone.Bomb, x, y)

  def revealValue(x: Int, y: Int): Field =
    if (!this.matrix.row(x)(y)._1.equals(Stone.NotTracked)) then this
    else copy(this.matrix.replaceCell(x, y, (this.matrix.row(x)(y)._2, this.matrix.row(x)(y)._1)))
