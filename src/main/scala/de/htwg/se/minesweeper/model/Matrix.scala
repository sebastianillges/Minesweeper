package de.htwg.se.minesweeper.model

import scala.reflect.ClassTag

case class Matrix[T: ClassTag](rows: Array[Array[T]]):
  def this(sizeX: Int, sizeY: Int, filling: T) = this(Array.tabulate[T](sizeX, sizeY)((row, col) => filling))
  val sizeY: Int = rows.length
  val sizeX: Int = rows(sizeY - 1).length
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int): Array[T] = rows(row)
  def fill(filling: T): Matrix[T] = copy(Array.tabulate(sizeX, sizeY)((row, col) => filling))
  def replaceCell(row: Int, col: Int, filling: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, filling)))
