import de.htwg.se.minesweeper.model.*
import controller.Controller
import aview.TUI

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = Field()
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run
