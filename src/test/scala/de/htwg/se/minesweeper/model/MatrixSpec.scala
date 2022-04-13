package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimensional Vector of something. A Matrix" when {
    "empty" should {
      "be created by using a x and a y dimension and a sample cell" in {
        val matrix = new Matrix[String, String](2, 3, ("x", "x"))
        matrix.rowNum should be(2)
        matrix.colNum should be(3)
      }
      "for test purposes only be created with an Vector of Vectors" in {
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
      "give access to it's cells " in {
        val testMatrix = new Matrix[Int, Int](2, 2, (0, 1))
        testMatrix.cell(0, 0) should be((0, 1))
      }
      "fill the matrix" in {
        val testMatrix = new Matrix[Int, Int](2, 2, (0, 0))
        val test = testMatrix.fill(1, 1)
        test.row(0) should be(Vector[(Int, Int)]((1, 1), (1, 1)))
      }
      "for test purposes only" in {
        val testMatrix = new Matrix[Int, Int](2, 2, (0, 0))
        testMatrix.row(0) should be(Vector[(Int, Int)]((0, 0), (0, 0)))
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
