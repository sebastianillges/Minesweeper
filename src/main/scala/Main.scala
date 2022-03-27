import scala.util.{CommandLineParser as CLP}

@main def main: Unit =
  println(eol + "Minesweeper matchfield:" + eol)
  // println(matchfield(8, 6, 3))
  println(betterMatchfield(8, 6, 5))

def eol = sys.props("line.separator")

def horizontal(cellWidth: Int = 5, cellNum: Int = 5) =
  ("+" + "-" * cellWidth) * cellNum + "+" + eol
def vertical(cellWidth: Int = 5, cellNum: Int = 5) =
  ("|" + " " * cellWidth) * cellNum + "|" + eol
def matchfield(height: Int = 3, width: Int = 3, cellWidth: Int = 3) =
  (horizontal(cellWidth, width)
    + vertical(cellWidth, width) * (cellWidth / 2))
    * height + horizontal(cellWidth, width)

def betterFirstHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "┌" + (("─" * cellWidth) + "┬") * (cellNum - 1) + ("─" * cellWidth) + "┐" + eol
def betterHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "├" + (("─" * cellWidth) + "┼") * (cellNum - 1) + ("─" * cellWidth) + "┤" + eol
def betterLastHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "└" + (("─" * cellWidth) + "┴") * (cellNum - 1) + ("─" * cellWidth) + "┘" + eol
def betterVertical(cellWidth: Int = 3, cellNum: Int = 3) =
  ("│" + (" " * cellWidth)) * cellNum + "│" + eol
def betterMatchfield(height: Int = 3, width: Int = 3, cellWidth: Int = 3) =
  betterFirstHorizontal(cellWidth, width)
    + betterVertical(cellWidth, width) * (cellWidth / 2)
    + (betterHorizontal(cellWidth, width) + betterVertical(
      cellWidth,
      width
    ) * (cellWidth / 2)) * (height - 1)
    + betterLastHorizontal(cellWidth, width)