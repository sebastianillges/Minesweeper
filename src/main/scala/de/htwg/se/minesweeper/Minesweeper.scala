import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field(4, 4, Stone.Flag)

  println(field.horizontal())
  println()
  println(field.lastHorizontal())
