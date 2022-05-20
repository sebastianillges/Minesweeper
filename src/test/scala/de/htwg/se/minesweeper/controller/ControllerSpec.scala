package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.*

class ControllerSpec extends AnyWordSpec {
  "The controller" when {
    val field = new Field(3, 3)
    val controller = new Controller(field)
    val eol = field.eol
    "revealValue-function is called" should {
      "have the tuple elements swap positions" in {
        val move = new Coordinates(0, 0)
        controller.revealValue(move).getCell(move.x, move.y)._1 should not be (Stone.NotTracked)
        controller.revealValue(move) should be(controller.revealValue(move))
      }
    }
    "nostep-function is called" should {
      "not change the field" in {
        controller.noStep(new Coordinates(0, 0)).toString should be(field.toString)
      }
    }
    "undo-function is called" should {
      "undo the previous move" in {
        controller.undo.getCell(0, 0)._1 should be(Stone.NotTracked)
      }
    }
    "redo-function is called" should {
      "redo the previous undo" in {
        controller.redo.getCell(0, 0)._1 should be(Stone.NotTracked)
      }
    }
    "toString-function is called" should {
      controller.toString should be(field.toString)
    }
    "getCell-function is called" should {
      controller.getCell(new Coordinates(0, 0)) should be(controller.field.matrix.cell(0, 0))
    }
    "setFlag-function is called" should {
      controller.setFlag(new Coordinates(0, 0)) should be(field.setFlag(0, 0))
    }
    "calculateBombAmount is called" should {
      controller.calculateBombAmount(field) should be(1)
    }
    "createFieldWithBombs is called" should {
      val helpField = controller.setBombs(controller.calculateBombAmount(field))
      "have as many bombs in its field as calculated" in {
        var count = 0
        helpField.detectBombs(helpField).foreach(x => if (x._2) then count = count + 1)
        count should be(1)
      }
    }
  }
}
