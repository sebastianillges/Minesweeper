package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.Field
import de.htwg.se.minesweeper.model.FileIOComponent.fileIoXmlImpl.FileIOXml
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FileIOXmlSpec extends AnyWordSpec {
  "The FileIO " when {
    val fieldE = new Field(8, 8)
    val fieldM = new Field(16, 16)
    val fieldH = new Field(32, 16)
    val xml = new FileIOXml()
    "load is called" should {
      "load the .xml file" in {
        xml.save(fieldE)
        xml.load.rows should be(8)
        xml.save(fieldM)
        xml.load.rows should be(16)
        xml.save(fieldH)
        xml.load.rows should be(32)
      }
    }
    "fieldToXml is called " should {
      "transform the field to xml" in {
        val field = new Field(1, 1)
        xml.fieldToXml(field).toString() should be(
          "<field rowSize=\"1\">\n      <cell row=\"0\" col=\"0\"><first>■</first><second>□</second><third>0</third>\n " +
            "   </cell>\n    </field>"
        )
      }
    }
    "cellToXml is called" should {
      "transform the cell of a field to xml" in {
        val field = new Field(1, 1)
        xml.cellToXml(field, 0, 0).toString() should be(
          "<cell row=\"0\" col=\"0\"><first>■</first>" +
            "<second>□</second><third>0</third>\n    </cell>"
        )
      }
    }
  }
}
