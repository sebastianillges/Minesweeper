import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.{DiffcultyFactory, DiffcultyStrategy}

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Minesweeper matchfield:")
  println("Insert 1 for easy, 2 for medium or 3 for hard: \n")

  val input = readLine
  println(
    "\n To reveal a Field Cell type in the cooridinates as for example: 00, \n" +
      " if you want to place a Flag in this Field write a f behind the cooridnates"
  )
  println("\n Press q to exit, u to undo and r to redo your move")
  val createField = DiffcultyFactory(input.toString)
  val controller = Controller(createField.run)
  val tui = TUI(controller)
  tui.run
