import de.htwg.se.model.Field

@main def main: Unit =
  println("Minesweeper matchfield:")
  println(new Field().matchfield(8, 6, 3))
