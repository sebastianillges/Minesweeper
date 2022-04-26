import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.*

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Minesweeper matchfield:")
  println("Insert 1 for easy, 2 for medium or 3 for hard: \n")

  val input = readLine
  var field: Option[Field] = None
  input match
    case "1" => field = Some(Field(matrix = new Matrix[Stone, Stone](8, 8, (Stone.NotTracked, Stone.EmptyTracked))))
    case "2" => field = Some(Field(matrix = new Matrix[Stone, Stone](16, 16, (Stone.NotTracked, Stone.EmptyTracked))))
    case "3" => field = Some(Field(matrix = new Matrix[Stone, Stone](32, 16, (Stone.NotTracked, Stone.EmptyTracked))))

  val controller = Controller(field.get)
  val tui = TUI(controller)
  tui.run
