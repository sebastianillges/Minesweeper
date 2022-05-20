package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.util.SetBombsTemplate

class SetBombsTemplateSpec extends AnyWordSpec {
  "When SetBombs Template is used" should {
    var field = new Field(2, 2)
    var field1 = new SetBomb()
    field = field1.setBombs(3, field)
    "set the number of bombs given as parameter in a field" in {
      field.detectBombAmount(field.detectBombs(field)) should be(3)
    }
  }
}
