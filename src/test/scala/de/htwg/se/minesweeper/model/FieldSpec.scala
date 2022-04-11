package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  "A Minesweeper Field" when {
    "created with default Stone" should {
      val field1 = new Field(1, 1, (Stone.NotTracked, Stone.NotTracked))
      val field2 = new Field(1, 2, (Stone.NotTracked, Stone.NotTracked))
      val field3 = new Field(2, 3, (Stone.NotTracked, Stone.NotTracked))
      val field = new Field(3, 3, (Stone.NotTracked, Stone.NotTracked))
      val eol = field1.eol
      "have a firstBar of form '┌───┬───┬───┐'" in {
        field.firstBar() should be("┌───┬───┬───┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and even cellWidth parameter" in {
        field2.firstBar(2, 1) should be("┌─┐" + eol)
        field2.firstBar(4, 1) should be("┌───┐" + eol)
        field2.firstBar(6, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and odd cellWidth parameter" in {
        field1.firstBar(1, 1) should be("┌─┐" + eol)
        field1.firstBar(3, 1) should be("┌───┐" + eol)
        field1.firstBar(5, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a bar of form '├───┼───┼───┤'" in {
        field.bar() should be("├───┼───┼───┤" + eol)
      }
      "have a scalable bar with odd cellWidth and even cellWidth parameter" in {
        field2.bar(2, 1) should be("├─┤" + eol)
        field2.bar(4, 1) should be("├───┤" + eol)
        field2.bar(6, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a scalable bar with odd cellWidth and odd cellWidth parameter" in {
        field1.bar(1, 1) should be("├─┤" + eol)
        field1.bar(3, 1) should be("├───┤" + eol)
        field1.bar(5, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a lastBar of form '└───┴───┴───┘'" in {
        field.lastBar() should be("└───┴───┴───┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and even cellWidth parameter" in {
        field2.lastBar(2, 1) should be("└─┘" + eol)
        field2.lastBar(4, 1) should be("└───┘" + eol)
        field2.lastBar(6, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and odd cellWidth parameter" in {
        field1.lastBar(1, 1) should be("└─┘" + eol)
        field1.lastBar(3, 1) should be("└───┘" + eol)
        field1.lastBar(5, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have cells as String of form '│ \u25A0 │ \u25A0 │ \u25A0 │'" in {
        field.cells(0) should be("│ \u25A0 │ \u25A0 │ \u25A0 │" + eol)
      }
      "have scalable cells" in {
        field1.cells(0, 1) should be("│\u25A0│" + eol)
        field2.cells(0, 1) should be("│\u25A0│\u25A0│" + eol)
        field.cells(0, 3) should be("│ \u25A0 │ \u25A0 │ \u25A0 │" + eol)
      }
      "have a matchField in the form " +
        "┌─┐" +
        "\u25A0" +
        "└─┘" in {
          field1.matchfield(1) should be("┌─┐" + eol + "│\u25A0│" + eol + "└─┘" + eol)
          field3.matchfield(3) should be(
            "┌───┬───┬───┐" + eol + "│ \u25A0 │ \u25A0 │ \u25A0 │" + eol + "├───┼───┼───┤" +
              eol + "│ \u25A0 │ \u25A0 │ \u25A0 │" + eol + "└───┴───┴───┘" + eol
          )
        }
    }
    "filled with ✴" should {
      val field1 = new Field(1, 1, (Stone.Bomb, Stone.Bomb))
      val field2 = new Field(1, 2, (Stone.Bomb, Stone.Bomb))
      val field3 = new Field(2, 3, (Stone.Bomb, Stone.Bomb))
      val field = new Field(3, 3, (Stone.Bomb, Stone.Bomb))
      val eol = field1.eol
      "have a firstBar of form '┌───┬───┬───┐'" in {
        field.firstBar() should be("┌───┬───┬───┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and even cellWidth parameter" in {
        field2.firstBar(2, 1) should be("┌─┐" + eol)
        field2.firstBar(4, 1) should be("┌───┐" + eol)
        field2.firstBar(6, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and odd cellWidth parameter" in {
        field1.firstBar(1, 1) should be("┌─┐" + eol)
        field1.firstBar(3, 1) should be("┌───┐" + eol)
        field1.firstBar(5, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a bar of form '├───┼───┼───┤'" in {
        field.bar() should be("├───┼───┼───┤" + eol)
      }
      "have a scalable bar with odd cellWidth and even cellWidth parameter" in {
        field2.bar(2, 1) should be("├─┤" + eol)
        field2.bar(4, 1) should be("├───┤" + eol)
        field2.bar(6, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a scalable bar with odd cellWidth and odd cellWidth parameter" in {
        field1.bar(1, 1) should be("├─┤" + eol)
        field1.bar(3, 1) should be("├───┤" + eol)
        field1.bar(5, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a lastBar of form '└───┴───┴───┘'" in {
        field.lastBar() should be("└───┴───┴───┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and even cellWidth parameter" in {
        field2.lastBar(2, 1) should be("└─┘" + eol)
        field2.lastBar(4, 1) should be("└───┘" + eol)
        field2.lastBar(6, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and odd cellWidth parameter" in {
        field1.lastBar(1, 1) should be("└─┘" + eol)
        field1.lastBar(3, 1) should be("└───┘" + eol)
        field1.lastBar(5, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have cells as String of form '│ ✴ │ ✴ │ ✴ │'" in {
        field.cells(0) should be("│ ✴ │ ✴ │ ✴ │" + eol)
      }
      "have scalable cells" in {
        field1.cells(0, 1) should be("│✴│" + eol)
        field2.cells(0, 1) should be("│✴│✴│" + eol)
        field.cells(0, 3) should be("│ ✴ │ ✴ │ ✴ │" + eol)
      }
      "have a matchField in the form " +
        "┌─┐" +
        "✴" +
        "└─┘" in {
          field1.matchfield(1) should be("┌─┐" + eol + "│✴│" + eol + "└─┘" + eol)
          field3.matchfield(3) should be(
            "┌───┬───┬───┐" + eol + "│ ✴ │ ✴ │ ✴ │" + eol + "├───┼───┼───┤" +
              eol + "│ ✴ │ ✴ │ ✴ │" + eol + "└───┴───┴───┘" + eol
          )
        }
    }
    "filled with \u2691" should {
      val field1 = new Field(1, 1, (Stone.Flag, Stone.Flag))
      val field2 = new Field(1, 2, (Stone.Flag, Stone.Flag))
      val field3 = new Field(2, 3, (Stone.Flag, Stone.Flag))
      val field = new Field(3, 3, (Stone.Flag, Stone.Flag))
      val eol = field1.eol
      "have a firstBar of form '┌───┬───┬───┐'" in {
        field.firstBar() should be("┌───┬───┬───┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and even cellWidth parameter" in {
        field2.firstBar(2, 1) should be("┌─┐" + eol)
        field2.firstBar(4, 1) should be("┌───┐" + eol)
        field2.firstBar(6, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a scalable firstBar with odd cellWidth and odd cellWidth parameter" in {
        field1.firstBar(1, 1) should be("┌─┐" + eol)
        field1.firstBar(3, 1) should be("┌───┐" + eol)
        field1.firstBar(5, 3) should be("┌─────┬─────┬─────┐" + eol)
      }
      "have a bar of form '├───┼───┼───┤'" in {
        field.bar() should be("├───┼───┼───┤" + eol)
      }
      "have a scalable bar with odd cellWidth and even cellWidth parameter" in {
        field2.bar(2, 1) should be("├─┤" + eol)
        field2.bar(4, 1) should be("├───┤" + eol)
        field2.bar(6, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a scalable bar with odd cellWidth and odd cellWidth parameter" in {
        field1.bar(1, 1) should be("├─┤" + eol)
        field1.bar(3, 1) should be("├───┤" + eol)
        field1.bar(5, 3) should be("├─────┼─────┼─────┤" + eol)
      }
      "have a lastBar of form '└───┴───┴───┘'" in {
        field.lastBar() should be("└───┴───┴───┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and even cellWidth parameter" in {
        field2.lastBar(2, 1) should be("└─┘" + eol)
        field2.lastBar(4, 1) should be("└───┘" + eol)
        field2.lastBar(6, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have a scalable lastBar with odd cellWidth and odd cellWidth parameter" in {
        field1.lastBar(1, 1) should be("└─┘" + eol)
        field1.lastBar(3, 1) should be("└───┘" + eol)
        field1.lastBar(5, 3) should be("└─────┴─────┴─────┘" + eol)
      }
      "have cells as String of form '│ \u2691 │ \u2691 │ \u2691 │'" in {
        field.cells(0) should be("│ \u2691 │ \u2691 │ \u2691 │" + eol)
      }
      "have scalable cells" in {
        field1.cells(0, 1) should be("│\u2691│" + eol)
        field2.cells(0, 1) should be("│\u2691│\u2691│" + eol)
        field.cells(0, 3) should be("│ \u2691 │ \u2691 │ \u2691 │" + eol)
      }
      "have a matchField in the form " +
        "┌─┐" +
        "\u2691" +
        "└─┘" in {
          field1.matchfield(1) should be("┌─┐" + eol + "│\u2691│" + eol + "└─┘" + eol)
          field3.matchfield(3) should be(
            "┌───┬───┬───┐" + eol + "│ \u2691 │ \u2691 │ \u2691 │" + eol + "├───┼───┼───┤" +
              eol + "│ \u2691 │ \u2691 │ \u2691 │" + eol + "└───┴───┴───┘" + eol
          )
        }
    }
    "put function used" should {
      val field = new Field(2, 2, (Stone.NotTracked, Stone.NotTracked))
      val eol = field.eol
      "have a different Playstone placed on the Field" in {
        field.put(Stone.Flag, 0, 0).toString should be(
          "┌───┬───┐" + eol +
            "│ ■ │ ■ │" + eol +
            "├───┼───┤" + eol +
            "│ ■ │ ■ │" + eol +
            "└───┴───┘" + eol
        )
      }
    }
  }
}
