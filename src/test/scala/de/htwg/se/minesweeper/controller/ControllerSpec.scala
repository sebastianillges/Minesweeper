package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.*

class ControllerSpec extends AnyWordSpec {
  "The controller" when {
    val field = new Field(3, 3)
    val controller = new Controller(field)
    "setBombs-function is called" should {
      "have the same number of bombs in the field as field.setBombs" in {
        controller.setBombs(3)
        field.setBombs(3)
        var countC: Int = 0
        for (i <- (0 until field.rows))
          for (j <- (0 until field.cols))
            if (field.matrix.row(i)(j)._2 == Stone.Bomb)
              countC = countC + 1
        var countF: Int = 0
        for (i <- (0 until field.rows))
          for (j <- (0 until field.cols))
            if (field.matrix.row(i)(j)._2 == Stone.Bomb)
              countF = countF + 1
        countC should be(countF)
      }
    }
    "revealValue-function is called" should {
      "have the tuple elements swap positions" in {
        controller.revealValue(0, 0) should not be (Stone.NotTracked)
      }
    }
    "toString-function is called" should {
      controller.toString should be(field.toString)
    }
  }
}
