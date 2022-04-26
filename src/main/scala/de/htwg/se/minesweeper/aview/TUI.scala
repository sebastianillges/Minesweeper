package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller

import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.model.Field

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run =
    if (controller.field.matrix.colNum == 8) then controller.field = controller.setBombs(10)
    else if (controller.field.matrix.colNum == 16) then controller.field = controller.setBombs(40)
    else if (controller.field.matrix.colNum == 16) then controller.field = controller.setBombs(99)
    println(controller.field.toString)
    getInputAndPrintLoop()

  override def update: Unit = println(controller.field.toString)

  def getInputAndPrintLoop(): Unit =
    val input = readLine
    parseInput(input) match
      case None =>
      case Some(newfield) =>
        println(controller.toString)
        getInputAndPrintLoop()

  def parseInput(input: String): Option[Field] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val x = chars(0).toString.toInt
        val y = chars(1).toString.toInt
        controller.field = controller.revealValue(x, y)
        Some(controller.field)
      }
