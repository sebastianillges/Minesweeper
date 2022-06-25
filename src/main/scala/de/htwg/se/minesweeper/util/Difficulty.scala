package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.{Field, Matrix, Stone}

trait Difficulty {
  def run: Field
}

private class Easy extends Difficulty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](8, 8, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

private class Medium extends Difficulty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](16, 16, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

private class Hard extends Difficulty {
  override def run: Field =
    Field(matrix = new Matrix[Stone, Stone, Int](32, 16, (Stone.NotTracked, Stone.EmptyTracked, 0)))
}

object DifficultyFactory {
  def apply(kind: String): Difficulty = kind match {
    case "1" => new Easy()
    case "2" => new Medium()
    case "3" => new Hard()
  }
}
