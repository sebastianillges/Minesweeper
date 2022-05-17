package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.model.{Field, Matrix, Stone}

trait CreateFieldFactory {
  def run: Field = Field(matrix = new Matrix[Stone, Stone](8, 8, (Stone.NotTracked, Stone.EmptyTracked)))
}

private class Easy extends CreateFieldFactory {
  override def run: Field = Field(matrix = new Matrix[Stone, Stone](8, 8, (Stone.NotTracked, Stone.EmptyTracked)))
}

private class Medium extends CreateFieldFactory {
  override def run: Field = Field(matrix = new Matrix[Stone, Stone](16, 16, (Stone.NotTracked, Stone.EmptyTracked)))
}

private class Hard extends CreateFieldFactory {
  override def run: Field = Field(matrix = new Matrix[Stone, Stone](32, 16, (Stone.NotTracked, Stone.EmptyTracked)))
}

object CreateFieldFactory {
  def apply(kind: String) = kind match {
    case "1" => new Easy()
    case "2" => new Medium()
    case "3" => new Hard()
  }
}
