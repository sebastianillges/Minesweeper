package de.htwg.se.minesweeper
import com.google.inject.Guice
import de.htwg.se.minesweeper.aview.{SwingGui, TUI}
import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.model.fileIoXmlImpl.FileIOXml
import de.htwg.se.minesweeper.util.{Difficulty, DifficultyFactory}

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

@main def main(): Unit =

  println("Minesweeper matchfield:")
  println("Insert 1 for easy, 2 for medium or 3 for hard: \n")

  val input = Try(readLine.toInt)
  input match {
    case Success(v) =>
      val difficulty = v
      println(
        "\n To reveal a Field Cell type in the cooridinates as for example: 00, \n" +
          " if you want to place a Flag in this Field write a f behind the cooridnates"
      )
      println("\n Press q to exit, u to undo and r to redo your move")
      val injector = Guice.createInjector(new MinesweeperModuleEasy)
      val controller = injector.getInstance(classOf[ControllerInterface])
      val tui = TUI(controller)
      val swing = new SwingGui(controller)
      tui.run()
    case Failure(e) => println("Ungültige Eingabe")
  }
