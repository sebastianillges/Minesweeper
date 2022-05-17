import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.CreateFieldFactory

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Minesweeper matchfield:")
  println("Insert 1 for easy, 2 for medium or 3 for hard: \n")

  val input = readLine
  val createField = CreateFieldFactory(input.toString)
  val controller = Controller(createField.run)
  val tui = TUI(controller)
  tui.run
