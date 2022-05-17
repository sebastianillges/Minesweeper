package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Coordinates, Field}
import de.htwg.se.minesweeper.util.Observer

import scala.io.StdIn.readLine
import scala.runtime.Nothing$

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run =
    controller.setBombs(controller.calculateBombAmount(controller.field))
    getInputAndPrintLoop()

  override def update: Unit = println(controller.toString)

  def getInputAndPrintLoop(): Unit =
    val input = readLine
    input.size match
      case 2 =>
        parseInput(input) match
          case None       => System.exit(0)
          case Some(move) => controller.doAndPublish(controller.revealValue, move)
      case 3 =>
        parseInput(input) match
          case None       => System.exit(0)
          case Some(move) => controller.doAndPublish(controller.setFlag, move)
      case _ =>
        input match
          case "q" => System.exit(0)
          case _   => println("Ungültige eingabe")

    getInputAndPrintLoop()

  def parseInput(input: String): Option[Coordinates] =
    val chars = input.toCharArray
    val x = chars(0).toString.toInt
    val y = chars(1).toString.toInt
    Some(new Coordinates(x, y))
