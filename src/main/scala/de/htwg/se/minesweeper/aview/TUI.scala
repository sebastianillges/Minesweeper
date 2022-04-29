package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller

import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.model.{Field, Coordinates}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run =
    controller.createFieldWithBombs
    getInputAndPrintLoop()

  override def update: Unit = println(controller.toString)

  def getInputAndPrintLoop(): Unit =
    val input = readLine
    parseInput(input) match
      case None       =>
      case Some(move) => controller.doAndPublish(controller.revealValue, move)
    getInputAndPrintLoop()

  def parseInput(input: String): Option[Coordinates] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val x = chars(0).toString.toInt
        val y = chars(1).toString.toInt
        Some(Coordinates(x, y))
      }
