@main def main: Unit =
  println(eol + "Minesweeper matchfield:" + eol)
  println(matchfield(8, 6, 3))

def matchfield(height: Int = 3, width: Int = 3, cellWidth: Int = 3) =
  ("   " * 25) + firstHorizontal(cellWidth, width)
    + (("   " * 25) + vertical(cellWidth, width) * (cellWidth / 2))
    + ((("   " * 25) + horizontal(cellWidth, width) + ("   " * 25) + vertical(
      cellWidth,
      width
    ) * (cellWidth / 2)) * (height - 1))
    + ("   " * 25) + lastHorizontal(cellWidth, width)

def firstHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "┌" + (("─" * cellWidth) + "┬") * (cellNum - 1) + ("─" * cellWidth) + "┐" + eol

def horizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "├" + (("─" * cellWidth) + "┼") * (cellNum - 1) + ("─" * cellWidth) + "┤" + eol

def lastHorizontal(cellWidth: Int = 3, cellNum: Int = 3) =
  "└" + (("─" * cellWidth) + "┴") * (cellNum - 1) + ("─" * cellWidth) + "┘" + eol

def vertical(cellHeight: Int = 3, cellNum: Int = 3) =
  ("│" + (" " * cellHeight)) * cellNum + "│" + eol

def eol = sys.props("line.separator")
