import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field(3, 3, Stone.NotTracked)

  println(field)
