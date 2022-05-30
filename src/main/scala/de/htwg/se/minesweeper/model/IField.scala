package de.htwg.se.minesweeper.model

trait IField:
  val cols: Int
  val rows: Int
  def firstBar(cellWidth: Int, row: Int): String
  def bar(cellWidth: Int, row: Int): String
  def lastBar(cellWidth: Int, row: Int): String
  def cells(row: Int, cellWidth: Int): String
  def matchfield(cellWidth: Int): String
  def getCell(x: Int, y: Int): (Stone, Stone, Int)
  def setBombs(bombNumber: Int): IField
  def revealValue(x: Int, y: Int): IField
  def setFlag(x: Int, y: Int): IField
  def detectBombs(): Map[Coordinates, Boolean]
  def detectFlags(): Map[Coordinates, Boolean]
  def calculateBombAmount(): Int
  def putValues(): IField
  def showValues(): IField
  def matrix: Matrix[Stone, Stone, Int]
