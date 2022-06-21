package de.htwg.se.minesweeper.model.fileIoXmlImpl

import com.google.inject.Guice
import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.ReplaceStrategy
import de.htwg.se.minesweeper.{MinesweeperModuleEasy, MinesweeperModuleHard, MinesweeperModuleMedium}

import java.io.*
import scala.xml.Equality.asRef
import scala.xml.NodeSeq.fromSeq
import scala.xml.{NodeSeq, PrettyPrinter, XML}

class FileIOXml extends FileIOInterface:

  override def load: FieldInterface =
    var field: FieldInterface = null
    val file = scala.xml.XML.loadFile("field.xml")
    val rowAttr = (file \\ "field" \ "@rowSize")
    val rowSize = rowAttr.text.toInt
    rowSize match {
      case 8 =>
        field = new Field(8, 8)
      case 16 =>
        field = new Field(16, 16)
      case 32 =>
        field = new Field(32, 16)
    }

    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes)
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val first: Stone = field.toStone((cell \ "first").text.trim)
      val second: Stone = field.toStone((cell \ "second").text.trim)
      val third: Int = (cell \ "third").text.trim.toInt
      field = field.setCell(row, col, (first, second, third))
    field

  override def save(field: FieldInterface): Unit =
    val pw = new PrintWriter(new File("field.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    pw.write(xml)
    pw.close()

  def fieldToXml(field: FieldInterface) =
    <field rowSize={field.rows.toString}>
      {
      for {
        row <- 0 until field.rows
        col <- 0 until field.cols
      } yield cellToXml(field, row, col)
    }
    </field>

  def cellToXml(field: FieldInterface, row: Int, col: Int) =
    <cell row ={row.toString} col ={col.toString}>{
      <first>{
        field.getCell(row, col)._1
      }</first>
        <second>{
        field.getCell(row, col)._2
      }</second>
        <third>{
        field.getCell(row, col)._3
      }</third>
    }
    </cell>
