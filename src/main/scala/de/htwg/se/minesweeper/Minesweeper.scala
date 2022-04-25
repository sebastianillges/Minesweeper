import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  var field: Field = Field(matrix = new Matrix[Stone, Stone](3, 3, (Stone.NotTracked, Stone.EmptyTracked)))
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run
