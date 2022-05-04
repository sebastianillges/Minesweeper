package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.*

class ControllerSpec extends AnyWordSpec {
  "The controller" when {
    val field = new Field(3, 3)
    val controller = new Controller(field)
    "revealValue-function is called" should {
      "have the tuple elements swap positions" in {
        controller.revealValue(Coordinates(0, 0)) should not be (Stone.NotTracked)
      }
    }
    "toString-function is called" should {
      controller.toString should be(field.toString)
    }

  }
}
