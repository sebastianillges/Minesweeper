import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field()

  println(field.cells(1, 1))
