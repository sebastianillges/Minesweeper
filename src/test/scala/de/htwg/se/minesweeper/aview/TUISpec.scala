package de.htwg.se.minesweeper.aview

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.controller.Controller

class TUISpec extends AnyWordSpec {
  "The TUI" should {
    val tui = TUI(Controller(new Field(3, 3, (Stone.NotTracked, Stone.EmptyTracked))))
    "recognize the input 00 as coordinates(0,0)" in {
      tui.parseInput("00") should be(Some(Coordinates(0, 0)))
    }
  }
}
