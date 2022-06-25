package de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case Bomb extends Stone("✴")
  case EmptyTracked extends Stone("\u25A1")
  case NotTracked extends Stone("\u25A0")
  case Flag extends Stone("\u2691")
  case One extends Stone("1")
  case Two extends Stone("2")
  case Three extends Stone("3")
  case Four extends Stone("4")
  case Five extends Stone("5")
  case Six extends Stone("6")
  case Seven extends Stone("7")
  case Eight extends Stone("8")
