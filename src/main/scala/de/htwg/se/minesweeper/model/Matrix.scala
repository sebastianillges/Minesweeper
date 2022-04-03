package de.htwg.se.minesweeper.model

import scala.reflect.ClassTag

case class Matrix[T: ClassTag](rows: Array[Array[T]]):
  def this(sizeRow: Int, sizeCol: Int, filling: T) = this(Array.tabulate[T](sizeRow, sizeCol)((row, col) => filling))
  val rowNum: Int = rows.length
  val colNum: Int = rows(rowNum - 1).length
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int): Array[T] = rows(row)
  def fill(filling: T): Matrix[T] = copy(Array.tabulate(rowNum, colNum) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
