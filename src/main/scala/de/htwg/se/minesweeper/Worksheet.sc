import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.RevealStrategy

import java.io.{File, PrintWriter}
import scala.xml.NodeSeq.{fromSeq, seqToNodeSeq}
import scala.xml.PrettyPrinter
import play.api.libs.json._

var field = new Field(3, 3)

field = field.setBombs(3)
field = field.showValues()
field = field.setFlag(0, 0)
val fieldddd = new Field(3, 3, (Stone.NotTracked, Stone.Bomb, 1))

val t = Json.obj(
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
        "first" -> Json.toJson(field.getCell(row, col)._1.toString),
        "second" -> Json.toJson(field.getCell(row, col)._2.toString),
        "third" -> Json.toJson(field.getCell(row, col)._3)
      )
    }
  )
)

println(t.fields.toString())
