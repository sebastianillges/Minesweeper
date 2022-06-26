package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*
import de.htwg.se.minesweeper.model.FileIOComponent.fileIoJsonImpl.FileIOJson
import play.api.libs.json.*

class FileIOJsonSpec extends AnyWordSpec {
  "The FileIO" when {
    val fieldE = new Field(8, 8)
    val fieldM = new Field(16, 16)
    val fieldH = new Field(32, 16)
    val json = new FileIOJson()
    "load is called " should {
      "load the .json file" in {
        json.save(fieldE)
        json.load.rows should be(8)
        json.save(fieldM)
        json.load.rows should be(16)
        json.save(fieldH)
        json.load.rows should be(32)
      }
    }
    "fieldToJson is called" should {
      "transform the field to json" in {
        val field = new Field(1, 1)
        Json.toJson(json.fieldToJson(field)).toString should be(
          "{\"field\":{\"sizeRow\":1,\"sizeCol\":1,\"cells\":" +
            "[{\"row\":0,\"col\":0,\"cell\":{\"first\":\"■\",\"second\":\"□\",\"third\":0}}]}}"
        )
      }
    }
  }
}
