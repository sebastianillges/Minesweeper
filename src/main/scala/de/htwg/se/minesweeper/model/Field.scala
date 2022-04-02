package de.htwg.se.minesweeper.model

case class Field(matrix: Matrix[Stone]):
  def this(sizeX: Int, sizeY: Int, filling: Stone) = this(new Matrix[Stone](sizeX, sizeY, filling))

  val sizeX: Int = matrix.sizeX
  val sizeY: Int = matrix.sizeY
  val eol: String = sys.props("line.separator")

  def firstHorizontal(cellWidth: Int = 3, col: Int = 3): String =
    "┌" + (("─" * cellWidth) + "┬") * (col - 1) + ("─" * cellWidth) + "┐" + eol

  def horizontal(cellWidth: Int = 3, col: Int = 3): String =
    "├" + (("─" * cellWidth) + "┼") * (col - 1) + ("─" * cellWidth) + "┤" + eol

  def lastHorizontal(cellWidth: Int = 3, col: Int = 3): String =
    "└" + (("─" * cellWidth) + "┴") * (col - 1) + ("─" * cellWidth) + "┘" + eol

  def vertical(cellWidth: Int = 3, row: Int = 3): String =
    matrix
      .row(row - 1)
      .map(_.toString)
      .map(" " * (cellWidth / 2) + _ + " " * (cellWidth / 2))
      .mkString("│", "│", "│") + eol

  def matchfield(col: Int = matrix.sizeY, row: Int = matrix.sizeX, cellSize: Int = 3): String =
    firstHorizontal(cellSize, col)
      + vertical(cellSize, col) * (cellSize / 2)
      + (horizontal(cellSize, col) + vertical(
        cellSize,
        col
      ) * (cellSize / 2)) * (row - 1)
      + lastHorizontal(cellSize, col)

  override def toString: String = matchfield()

  def put(x: Int, y: Int, stone: Stone): Field = copy(matrix.replaceCell(x, y, stone))
