package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.*

object ReplaceStrategy:
  def strategy(stonePosition: Boolean, field: Field, x: Int, y: Int, stone: Stone) =
    if (stonePosition) then strategy1(field, x, y, stone) else strategy2(field, x, y, stone)

  private def strategy1(field: Field, x: Int, y: Int, stone: Stone) =
    field.copy(field.matrix.replaceCell(x, y, (stone, field.matrix.row(x)(y)._2)))

  private def strategy2(field: Field, x: Int, y: Int, stone: Stone) =
    field.copy(field.matrix.replaceCell(x, y, (field.matrix.row(x)(y)._1, stone)))
