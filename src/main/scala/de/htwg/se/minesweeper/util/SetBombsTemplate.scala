package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.*
import scala.util.Random as r

trait SetBombsTemplate:
  def setBombs(bombNumber: Int, field: Field): Field =
    setBombsR(bombNumber, field)

  def setBombsR(bombNumber: Int, field: Field, count: Int = 0): Field

class Test extends SetBombsTemplate:
  override def setBombsR(bombNumber: Int, field: Field, count: Int): Field =
    var row = r.nextInt(field.rows)
    val col = r.nextInt(field.cols)
    if (count == bombNumber) then field
    else if (field.matrix.row(row)(col)._2.equals(Stone.Bomb)) then setBombsR(bombNumber, field, count)
    else
      val resField = ReplaceStrategy.strategy(false, field, row, col, Stone.Bomb)
      val countR = count + 1
      setBombsR(bombNumber, resField, countR)
