package de.htwg.se.minesweeper.model

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case Bomb extends Stone("✴")
  case EmptyTracked extends Stone("\u25A1")
  case NotTracked extends Stone("\u25A0")
  case Flag extends Stone("\u2691")
