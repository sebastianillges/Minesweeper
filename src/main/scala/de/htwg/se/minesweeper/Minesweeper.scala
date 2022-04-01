import de.htwg.se.minesweeper.model._

@main def main: Unit =
  println("Minesweeper matchfield: \n")
  var field = new Field(size = 5, filling = Stone.NotTracked)
  println(field.toString)
