package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimensional Array of something. A Matrix" when {
    "empty" should {
      "be created by using a x and a y dimension and a sample cell" in {
        val matrix = new Matrix[String, String](2, 3, ("x", "x"))
        matrix.rowNum should be(2)
        matrix.colNum should be(3)
      }
      "for test purposes only be created with an Array of Arrays" in {
        val testMatrix = new Matrix[String, String](
          Vector(Vector(("a", "a"), ("b", "b"), ("c", "c")), Vector(("1", "1"), ("2", "2"), ("3", "3")))
        )
        testMatrix.rowNum should be(2)
        testMatrix.colNum should be(3)
      }
      "return a row" in {
        val testMatrix = new Matrix[String, String](2, 3, ("x", "x"))
        testMatrix.row(0) should be(Vector[(String, String)](("x", "x"), ("x", "x"), ("x", "x")))
        testMatrix.row(1) should be(Vector[(String, String)](("x", "x"), ("x", "x"), ("x", "x")))
      }
    }
    "filled" should {
      val matrix = new Matrix[String, String](2, 3, ("x", "x"))
      "give access to its cells" in {
        matrix.cell(0, 1) should be(("x", "x"))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 1, ("o", "o"))
        matrix.cell(0, 1) should be(("x", "x"))
        returnedMatrix.cell(0, 1) should be(("o", "o"))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(("o", "o"))
        matrix.cell(0, 0) should be(("x", "x"))
        matrix.cell(1, 1) should be(("x", "x"))
        returnedMatrix.cell(1, 1) should be(("o", "o"))
        returnedMatrix.cell(1, 1) should be(("o", "o"))
      }
    }
  }
}
