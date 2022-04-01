@main def main: Unit =
  println("Minesweeper matchfield:" + eol)
  println(matchfield(8, 6, 10))

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
  firstHorizontal(cellWidth, width)
    + vertical(cellWidth, width) * (cellWidth / 2)
    + (horizontal(cellWidth, width) + vertical(
      cellWidth,
      width
    ) * (cellWidth / 2)) * (height - 1)
    + lastHorizontal(cellWidth, width)

// Test
