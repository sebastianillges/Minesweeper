import controller.Controller
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run =
    println(controller.toString)
    getInputAndPrintLoop()

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
        Some(controller.revealValue(x, y))
      }