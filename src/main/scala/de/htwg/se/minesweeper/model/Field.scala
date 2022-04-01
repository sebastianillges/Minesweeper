package de.htwg.se.minesweeper.model

case class Field(matrix: Matrix[Stone]):
  def this(size: Int, filling: Stone) = this(new Matrix(size, filling))
  val size = matrix.size

  def matchfield(height: Int = 3, cellWidth: Int = 3) =
    (0 until size)
      .map(vertical(_, cellWidth))
      .mkString(firstHorizontal(cellWidth, size), horizontal(cellWidth, size), lastHorizontal(cellWidth, size))

  def firstHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
    "┌" + (("─" * cellWidth) + "┬") * (cellNum - 1) + ("─" * cellWidth) + "┐" + eol

  def horizontal(cellWidth: Int = 3, cellNum: Int = 3) =
    "├" + (("─" * cellWidth) + "┼") * (cellNum - 1) + ("─" * cellWidth) + "┤" + eol

  def lastHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
    "└" + (("─" * cellWidth) + "┴") * (cellNum - 1) + ("─" * cellWidth) + "┘" + eol

  def vertical(cellHeight: Int = 3, cellNum: Int = 3) =
    matrix
      .rows(cellHeight)
      .map(_.toString)
      .map(" " * ((cellNum - 1) / 2) + _ + " " * ((cellNum - 1) / 2))
      .mkString("|", "|", "|") + eol

  def eol = sys.props("line.separator")
  def put(stone: Stone, x: Int, y: Int) = copy(matrix.replaceCell(x, y, stone))

  override def toString = matchfield()
