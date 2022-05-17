package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.util
import de.htwg.se.minesweeper.util.CopyStrategy
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class CopyStrategySpec extends AnyWordSpec {
  "When CopyStrategy Pattern is used" should {
    var field = new Field(3, 3)
    field = CopyStrategy(field, 0, 0, Stone.Bomb, true).strategy
    field = util.CopyStrategy(field, 0, 0, Stone.NotTracked, false).strategy
    "replace the cell with the given stone, if true as parameter, then first tuple place else second" in {
      field.matrix.rows(0)(0)._1 should be(Stone.Bomb)
      field.matrix.rows(0)(0)._2 should be(Stone.NotTracked)
    }
  }
}
