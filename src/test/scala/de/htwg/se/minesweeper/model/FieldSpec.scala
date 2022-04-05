package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model._

class FieldSpec extends AnyWordSpec {
  "Field" should {
    "have a firstHorizontal as String of form '┌───┬───┬───┐'" in {
      val field = new Field()
      field.firstHorizontal() should be("┌───┬───┬───┐" + field.eol)
    }
    "have a horizontal as String of form '├───┼───┼───┤'" in {
      val field = new Field()
      field.horizontal() should be("├───┼───┼───┤" + field.eol)
    }
    "have a lastHorizontal as String of form '└───┴───┴───┘'" in {
      val field = new Field()
      field.lastHorizontal() should be("└───┴───┴───┘" + field.eol)
    }
    "have a vertical as String of form '│   │   │   │'" in {
      val field = new Field()
      field.vertical() should be("│   │   │   │" + field.eol)
    }
    "have a scalable firstHorizontal of form '┌─┬─┐', '┌─┬─┬─┐', etc." in {
      val field = new Field()
      field.firstHorizontal(1, 1) should be("┌─┐" + field.eol)
      field.firstHorizontal(2, 1) should be("┌─┐" + field.eol)
      field.firstHorizontal(3, 1) should be("┌───┐" + field.eol)
      field.firstHorizontal(4, 1) should be("┌───┐" + field.eol)

      field.firstHorizontal(1, 2) should be("┌─┬─┐" + field.eol)
      field.firstHorizontal(1, 3) should be("┌─┬─┬─┐" + field.eol)

      field.firstHorizontal(2, 2) should be("┌─┬─┐" + field.eol)
      field.firstHorizontal(3, 3) should be("┌───┬───┬───┐" + field.eol)
    }
    "have a scalable horizontal" in {
      val field = new Field()
      field.horizontal(1, 1) should be("├─┤" + field.eol)
      field.horizontal(2, 1) should be("├─┤" + field.eol)
      field.horizontal(3, 1) should be("├───┤" + field.eol)
      field.horizontal(4, 1) should be("├───┤" + field.eol)

      field.horizontal(1, 2) should be("├─┼─┤" + field.eol)
      field.horizontal(1, 3) should be("├─┼─┼─┤" + field.eol)

      field.horizontal(2, 2) should be("├─┼─┤" + field.eol)
      field.horizontal(3, 3) should be("├───┼───┼───┤" + field.eol)
    }
    "have a scalable lastHorizontal" in {
      val field = new Field()
      field.lastHorizontal(1, 1) should be("└─┘" + field.eol)
      field.lastHorizontal(2, 1) should be("└─┘" + field.eol)
      field.lastHorizontal(3, 1) should be("└───┘" + field.eol)
      field.lastHorizontal(4, 1) should be("└───┘" + field.eol)

      field.lastHorizontal(1, 2) should be("└─┴─┘" + field.eol)
      field.lastHorizontal(1, 3) should be("└─┴─┴─┘" + field.eol)

      field.lastHorizontal(2, 2) should be("└─┴─┘" + field.eol)
      field.lastHorizontal(3, 3) should be("└───┴───┴───┘" + field.eol)
    }
    "have a scalable vertical" in {
      val field = new Field()
      field.vertical() should be("│   │   │   │" + field.eol)
    }
  }
}
