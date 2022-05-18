package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.*

trait CopyStrategy {
  def run(field: Field, x: Int, y: Int, stone: Stone): Field
}

private class Strategy1 extends CopyStrategy:
  override def run(field: Field, x: Int, y: Int, stone: Stone): Field =
    field.copy(field.matrix.replaceCell(x, y, (stone, field.matrix.row(x)(y)._2)))

private class Strategy2 extends CopyStrategy:
  override def run(field: Field, x: Int, y: Int, stone: Stone): Field =
    field.copy(field.matrix.replaceCell(x, y, (field.matrix.row(x)(y)._1, stone)))

object CopyFactory {
  def apply(kind: Boolean, field: Field, x: Int, y: Int, stone: Stone) = kind match {
    case true  => new Strategy1().run(field, x, y, stone)
    case false => new Strategy2().run(field, x, y, stone)
  }
}
