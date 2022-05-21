package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.{Field, Matrix, Stone}

trait Diffculty {
  def run: Field
}

private class Easy extends Diffculty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](8, 8, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

private class Medium extends Diffculty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](16, 16, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

private class Hard extends Diffculty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](32, 16, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

object DiffcultyFactory {
  def apply(kind: String) = kind match {
    case "1" => new Easy()
    case "2" => new Medium()
    case "3" => new Hard()
  }
}
