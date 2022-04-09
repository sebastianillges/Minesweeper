package de.htwg.se.minesweeper.model

case class Matrix[S, T](rows: Vector[Vector[(S, T)]]):
  def this(sizeRow: Int, sizeCol: Int, filling: (S, T)) =
    this(Vector.tabulate[(S, T)](sizeRow, sizeCol)((row, col) => filling))
  val rowNum: Int = rows.length
  val colNum: Int = rows(rowNum - 1).length
  def cell(row: Int, col: Int): (S, T) = rows(row)(col)
  def row(rowIndex: Int): Vector[(S, T)] = rows(rowIndex)
  def fill(filling: (S, T)): Matrix[S, T] = copy(Vector.tabulate(rowNum, colNum) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: (S, T)): Matrix[S, T] = copy(
    rows.updated(row, rows(row).updated(col, cell))
  )
