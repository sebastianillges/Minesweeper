package de.htwg.se.minesweeper.aview

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.controller.controllerComponent.*
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*

class TUISpec extends AnyWordSpec {
  "The TUI" should {
    val tui = TUI(Controller(new Field(3, 3, (Stone.NotTracked, Stone.EmptyTracked, 0))))
    "recognize the input 00 as coordinates(0,0)" in {
      tui.parseInput("00") should be(Some(new Coordinates(0, 0)))
    }
    "recognize the input 00f as coorinates(0,0) and extra char to set the flag to the coordinates" in {
      tui.parseInput("00f") should be(Some(new Coordinates(0, 0, ' ')))
    }
  }
}
