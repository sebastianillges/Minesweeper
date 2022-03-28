@main def main: Unit =
  println(eol + "Minesweeper matchfield:" + eol)
  println(matchfield(8, 6, 3))

def eol = sys.props("line.separator")

def firstHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "┌" + (("─" * cellWidth) + "┬") * (cellNum - 1) + ("─" * cellWidth) + "┐" + eol
def horizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "├" + (("─" * cellWidth) + "┼") * (cellNum - 1) + ("─" * cellWidth) + "┤" + eol
def lastHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "└" + (("─" * cellWidth) + "┴") * (cellNum - 1) + ("─" * cellWidth) + "┘" + eol
def vertical(cellHeight: Int = 3, cellNum: Int = 3) =
  ("│" + (" " * cellHeight)) * cellNum + "│" + eol
def matchfield(height: Int = 3, width: Int = 3, cellWidth: Int = 3) =
  ("   " * 20) + firstHorizontal(cellWidth, width)
      + (("   " * 20) + vertical(cellWidth, width) * (cellWidth / 2))
      + ((("   " * 20) + horizontal(cellWidth, width) + ("   " * 20) + vertical(cellWidth, width) * (cellWidth / 2)) * (height - 1))
      + ("   " * 20) + lastHorizontal(cellWidth, width)
