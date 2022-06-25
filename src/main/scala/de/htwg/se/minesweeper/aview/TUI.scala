package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.controllerComponent.*
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.{Coordinates, Field}
import de.htwg.se.minesweeper.util.{Event, Observer}

import scala.io.StdIn.readLine
import scala.runtime.Nothing$

class TUI(controller: ControllerInterface) extends Observer:
  controller.add(this)
  var continue = true
  def run(): Any =
    controller.setBombs(controller.calculateBombAmount())
    getInputAndPrintLoop

  override def update(e: Event): Unit =
    e match
      case Event.Quit => continue = false
      case Event.Move =>
        println("Flaggen verfügbar:" + controller.flagsLeft());
        println(controller.toString)

  def getInputAndPrintLoop: Any =
    val input = readLine
    input.length match
      case 1 =>
        input match
          case "q" => System.exit(0)
          case "u" => controller.doAndPublish(controller.undo)
          case "r" => controller.doAndPublish(controller.redo)
          case "s" => controller.doAndPublish(controller.save)
          case "l" => controller.doAndPublish(controller.load)
          case "1" => controller.doAndPublish(controller.createNewField("1"))
          case "2" => controller.doAndPublish(controller.createNewField("2"))
          case "3" => controller.doAndPublish(controller.createNewField("3"))
          case _   => println("Ungültige eingabe")
      case 2 =>
        parseInput(input) match
          case None       => System.exit(0)
          case Some(move) => controller.doAndPublish(controller.revealValue, move)
      case 3 =>
        val chars = input.toCharArray
        val helpFlag = chars(2).toString
        helpFlag match
          case "f" =>
            parseInput(input) match
              case None       => System.exit(0)
              case Some(move) => controller.doAndPublish(controller.setFlag, move);
          case "F" =>
            parseInput(input) match
              case None       => System.exit(0)
              case Some(move) => controller.doAndPublish(controller.setFlag, move);
          case _ => println("Ungültige eingabe")
      case _ => println("Ungültige eingabe")

    if continue then getInputAndPrintLoop

  def parseInput(input: String): Option[Coordinates] =
    val chars = input.toCharArray
    val x = chars(0).toString.toInt
    val y = chars(1).toString.toInt
    Some(new Coordinates(x, y))
