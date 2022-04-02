import de.htwg.se.minesweeper.model.*

@main def main(): Unit =
  println("Minesweeper matchfield: \n")
  var field = new Field(1, 4, Stone.Flag)

  println(field.matchfield())

  println(field.matrix.sizeX)
  println(field.matrix.sizeY)
