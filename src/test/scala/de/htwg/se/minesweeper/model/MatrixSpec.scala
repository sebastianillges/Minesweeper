package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.Matrix
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimensional Vector of something. A Matrix" when {
    "empty" should {
      "be created by using a x and a y dimension and a sample cell" in {
        val matrix = new Matrix[String, String, Int](2, 3, ("x", "x", 0))
        matrix.rowNum should be(2)
        matrix.colNum should be(3)
      }
      "for test purposes only be created with an Vector of Vectors" in {
        val testMatrix = new Matrix[String, String, Int](
          Vector(
            Vector(("a", "a", 0), ("b", "b", 0), ("c", "c", 0)),
            Vector(("1", "1", 0), ("2", "2", 0), ("3", "3", 0))
          )
        )
        testMatrix.rowNum should be(2)
        testMatrix.colNum should be(3)
      }
      "return a row" in {
        val testMatrix = new Matrix[String, String, Int](2, 3, ("x", "x", 0))
        testMatrix.row(0) should be(Vector[(String, String, Int)](("x", "x", 0), ("x", "x", 0), ("x", "x", 0)))
        testMatrix.row(1) should be(Vector[(String, String, Int)](("x", "x", 0), ("x", "x", 0), ("x", "x", 0)))
      }
      "give access to it's cells " in {
        val testMatrix = new Matrix[Int, Int, Int](2, 2, (0, 1, 0))
        testMatrix.cell(0, 0) should be((0, 1, 0))
      }
      "fill the matrix" in {
        val testMatrix = new Matrix[Int, Int, Int](2, 2, (0, 0, 0))
        val test = testMatrix.fill(1, 1, 0)
        test.row(0) should be(Vector[(Int, Int, Int)]((1, 1, 0), (1, 1, 0)))
      }
      "for test purposes only" in {
        val testMatrix = new Matrix[Int, Int, Int](2, 2, (0, 0, 0))
        testMatrix.row(0) should be(Vector[(Int, Int, Int)]((0, 0, 0), (0, 0, 0)))
      }
    }
    "filled" should {
      val matrix = new Matrix[String, String, Int](2, 3, ("x", "x", 0))
      "give access to its cells" in {
        matrix.cell(0, 1) should be(("x", "x", 0))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 1, ("o", "o", 0))
        matrix.cell(0, 1) should be(("x", "x", 0))
        returnedMatrix.cell(0, 1) should be(("o", "o", 0))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(("o", "o", 0))
        matrix.cell(0, 0) should be(("x", "x", 0))
        matrix.cell(1, 1) should be(("x", "x", 0))
        returnedMatrix.cell(1, 1) should be(("o", "o", 0))
        returnedMatrix.cell(1, 1) should be(("o", "o", 0))
      }
    }
  }
}
