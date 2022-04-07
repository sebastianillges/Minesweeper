import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field()

  println(field.firstBar(1, 1))
  println(field.firstBar(2, 1))
  println(field.firstBar(3, 1))
  println(field.firstBar(4, 1))
