package de.htwg.se.minesweeper.model

case class Field(height: Int = 3, width: Int = 3, cellWidth: Int = 3):
  def eol = sys.props("line.separator")

  def firstBar(cellWidth: Int = 3, cellNum: Int = 3) =
    "┌" + (("─" * cellWidth) + "┬") * (cellNum - 1) + ("─" * cellWidth) + "┐" + eol
  def bar(cellWidth: Int = 3, cellNum: Int = 3) =
    "├" + (("─" * cellWidth) + "┼") * (cellNum - 1) + ("─" * cellWidth) + "┤" + eol
  def lastBar(cellWidth: Int = 3, cellNum: Int = 3) =
    "└" + (("─" * cellWidth) + "┴") * (cellNum - 1) + ("─" * cellWidth) + "┘" + eol
  def cells(cellWidth: Int = 3, cellNum: Int = 3) =
    ("│" + (" " * cellWidth)) * cellNum + "│" + eol
  def matchfield(height: Int, width: Int, cellWidth: Int) =
    firstBar(cellWidth, width)
      + cells(cellWidth, width)
      + (bar(cellWidth, width) + cells(
        cellWidth,
        width
      )) * (height - 1)
      + lastBar(cellWidth, width)
