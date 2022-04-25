import model.Field
import model.Stone
import util.Observable
import java.util.Observable

case class Controller(var field: Field) extends Observable:

  def put(stone: Stone, x: Int, y: Int) = field.put(stone, x, y)

  def setBombs(bombNumber: Int) = field.setBombs(bombNumber)

  def revealValue(x: Int, y: Int) = field.revealValue(x, y)

  notifyObservers

  override def toString = field.toString
