import de.htwg.se.minesweeper.model.*
import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Minesweeper matchfield: \n")

  val field = new Field(2, 2, (Stone.NotTracked, Stone.NotTracked))
  field.put(Stone.Flag, 0, 0)
  println(field)

  // val test = new Matrix[Stone, Stone](3, 3, (Stone.NotTracked, Stone.One))
  // var field = Field(test)
  // field = field.setBombs(4)
  // getInputAndPrintLoop(field)

def getInputAndPrintLoop(field: Field): Unit =
  val input = readLine
  parseInput(input) match
    case None => field
    case Some(newfield) =>
      println(newfield)
      getInputAndPrintLoop(newfield)

  def parseInput(input: String): Option[Field] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val x = chars(0).toString.toInt
        val y = chars(1).toString.toInt
        Some(field.revealValue(x, y))
      }
