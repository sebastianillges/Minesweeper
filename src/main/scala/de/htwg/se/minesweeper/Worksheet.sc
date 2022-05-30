import de.htwg.se.minesweeper.model.*
import de.htwg.se.minesweeper.util.RevealStrategy

var field = new Field(3, 3)

field = field.setBombs(3)
field = field.putValues()
field = field.showValues()
field = RevealStrategy.strategy(0, 0, field)
field = field.setFlag(0, 0)
