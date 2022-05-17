package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.{Field, Stone}

case class CopyStrategy(field: Field, x: Int, y: Int, stone: Stone, stonePosition: Boolean) {
  var strategy = if (stonePosition) then strategy1 else strategy2

  def strategy1 =
    field.put_1(stone, x, y)

  def strategy2 =
    field.put_2(stone, x, y)
}
