package de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl

case class Matrix[S, T, U](rows: Vector[Vector[(S, T, U)]]):
  def this(sizeRow: Int, sizeCol: Int, filling: (S, T, U)) =
    this(Vector.tabulate[(S, T, U)](sizeRow, sizeCol)((row, col) => filling))
  val rowNum: Int = rows.length
  val colNum: Int = rows(0).length
  def cell(row: Int, col: Int): (S, T, U) = rows(row)(col)
  def row(rowIndex: Int): Vector[(S, T, U)] = rows(rowIndex)
  def fill(filling: (S, T, U)): Matrix[S, T, U] = copy(Vector.tabulate(rowNum, colNum) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: (S, T, U)): Matrix[S, T, U] = copy(
    rows.updated(row, rows(row).updated(col, cell))
  )
