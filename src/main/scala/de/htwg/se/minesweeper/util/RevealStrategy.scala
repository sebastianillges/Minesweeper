package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.{Field, Stone}

object RevealStrategy:
  def strategy(x: Int, y: Int, field: Field): Field = if (field.getCell(x, y)._2.equals(Stone.Bomb)) then
    strategy1(x, y, field)
  else strategy2(x, y, field)

  private def swap(x: Int, y: Int, field: Field): Field =
    field.copy(field.matrix.replaceCell(x, y, (field.getCell(x, y)._2, field.getCell(x, y)._1)))

  private def strategy1(x: Int, y: Int, field: Field): Field =
    var res: Field = field
    for (i <- (0 until field.rows))
      for (j <- (0 until field.cols))
        if (!field.getCell(i, j)._2.equals(Stone.NotTracked)) then res = swap(i, j, res)
    res

  private def strategy2(x: Int, y: Int, field: Field): Field =
    swap(x, y, field)
