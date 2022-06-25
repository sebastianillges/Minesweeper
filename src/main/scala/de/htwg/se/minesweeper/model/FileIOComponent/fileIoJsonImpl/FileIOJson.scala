package de.htwg.se.minesweeper.model.FileIOComponent.fileIoJsonImpl

import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*
import de.htwg.se.minesweeper.model.FileIOComponent.*
import play.api.libs.json.*

import java.io.*
import scala.io.Source

class FileIOJson extends FileIOInterface:

  override def load: FieldInterface =
    var field: FieldInterface = null
    val source: String = Source.fromFile("field.json").getLines().mkString
    val json: JsValue = Json.parse(source)
    val sizeRow = (json \ "field" \ "sizeRow").get.toString.toInt
    val sizeCol = (json \ "field" \ "sizeCol").get.toString.toInt
    sizeRow match {
      case 8 =>
        field = new Field(8, 8)
      case 16 =>
        field = new Field(16, 16)
      case 32 =>
        field = new Field(32, 16)
    }
    for (index <- 0 until sizeRow * sizeCol)
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val first = field.toStone((json \\ "first")(index).as[String])
      val second = field.toStone((json \\ "second")(index).as[String])
      val third = (json \\ "third")(index).as[Int]
      field = field.setCell(row, col, (first, second, third))
    field

  override def save(field: FieldInterface): Unit =
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field)))
    pw.close()

  def fieldToJson(field: FieldInterface) =
    Json.obj(
      "field" -> Json.obj(
        "sizeRow" -> field.rows,
        "sizeCol" -> field.cols,
        "cells" -> Json.toJson(
          for {
            row <- 0 until field.rows;
            col <- 0 until field.cols
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.obj(
                "first" -> Json.toJson(field.getCell(row, col)._1.toString),
                "second" -> Json.toJson(field.getCell(row, col)._2.toString),
                "third" -> Json.toJson(field.getCell(row, col)._3)
              )
            )
          }
        )
      )
    )
