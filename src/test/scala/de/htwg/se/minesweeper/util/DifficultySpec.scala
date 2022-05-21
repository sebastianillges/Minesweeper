package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class DifficultySpec extends AnyWordSpec {
  "When DifficultyStrategy Pattern is used" should {
    var field1 = DiffcultyFactory("1")
    "create a field in easy Mode" in {
      field1.run.rows should be(8)
      field1.run.cols should be(8)
    }
    var field2 = DiffcultyFactory("2")
    "create a field in medium mode" in {
      field2.run.rows should be(16)
      field2.run.cols should be(16)
    }
    var field3 = DiffcultyFactory("3")
    "create a field in hard mode" in {
      field3.run.rows should be(32)
      field3.run.cols should be(16)
    }
  }
}
