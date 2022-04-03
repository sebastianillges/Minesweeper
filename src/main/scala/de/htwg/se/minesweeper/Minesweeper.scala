import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field(6, 5, Stone.Bomb)
  println(field.matchfield())
