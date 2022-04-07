package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model._

class FieldSpec extends AnyWordSpec {
  "Field" should {
    "have a firstBar as String of form '┌───┬───┬───┐'" in {
      val field = new Field()
      field.firstBar() should be("┌───┬───┬───┐" + field.eol)
    }
    "have a bar as String of form '├───┼───┼───┤'" in {
      val field = new Field()
      field.bar() should be("├───┼───┼───┤" + field.eol)
    }
    "have a lastBar as String of form '└───┴───┴───┘'" in {
      val field = new Field()
      field.lastBar() should be("└───┴───┴───┘" + field.eol)
    }
    "have cells as String of form '│   │   │   │'" in {
      val field = new Field()
      field.cells() should be("│   │   │   │" + field.eol)
    }
    "have a scalable firstBar of form '┌─┬─┐', '┌─┬─┬─┐', etc." in {
      val field = new Field()
      field.firstBar(1, 1) should be("┌─┐" + field.eol)
      field.firstBar(2, 1) should be("┌─┐" + field.eol)
      field.firstBar(3, 1) should be("┌───┐" + field.eol)
      field.firstBar(4, 1) should be("┌───┐" + field.eol)

      field.firstBar(1, 2) should be("┌─┬─┐" + field.eol)
      field.firstBar(1, 3) should be("┌─┬─┬─┐" + field.eol)

      field.firstBar(2, 2) should be("┌─┬─┐" + field.eol)
      field.firstBar(3, 3) should be("┌───┬───┬───┐" + field.eol)
    }
    "have a scalable bar" in {
      val field = new Field()
      field.bar(1, 1) should be("├─┤" + field.eol)
      field.bar(2, 1) should be("├─┤" + field.eol)
      field.bar(3, 1) should be("├───┤" + field.eol)
      field.bar(4, 1) should be("├───┤" + field.eol)

      field.bar(1, 2) should be("├─┼─┤" + field.eol)
      field.bar(1, 3) should be("├─┼─┼─┤" + field.eol)

      field.bar(2, 2) should be("├─┼─┤" + field.eol)
      field.bar(3, 3) should be("├───┼───┼───┤" + field.eol)
    }
    "have a scalable lastBar" in {
      val field = new Field()
      field.lastBar(1, 1) should be("└─┘" + field.eol)
      field.lastBar(2, 1) should be("└─┘" + field.eol)
      field.lastBar(3, 1) should be("└───┘" + field.eol)
      field.lastBar(4, 1) should be("└───┘" + field.eol)

      field.lastBar(1, 2) should be("└─┴─┘" + field.eol)
      field.lastBar(1, 3) should be("└─┴─┴─┘" + field.eol)

      field.lastBar(2, 2) should be("└─┴─┘" + field.eol)
      field.lastBar(3, 3) should be("└───┴───┴───┘" + field.eol)
    }
    "have scalable cells" in {
      val field = new Field()
      field.cells() should be("│   │   │   │" + field.eol)

      field.cells(1, 1) should be("│ │" + field.eol)
      field.cells(1, 2) should be("│ │ │" + field.eol)
      field.cells(1, 3) should be("│ │ │ │" + field.eol)

      field.cells(2, 1) should be("│  │" + field.eol)
      field.cells(3, 1) should be("│   │" + field.eol)

      field.cells(2, 2) should be("│  │  │" + field.eol)
      field.cells(3, 3) should be("│   │   │   │" + field.eol)
    }
  }
}
