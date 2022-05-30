package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Coordinates, IField, Stone}
import de.htwg.se.minesweeper.util.Observable

trait IController extends Observable:
  def doAndPublish(doThis: Coordinates => IField, coordinates: Coordinates): Unit
  def doAndPublish(doThis: => IField): Unit
  def quit(): Unit
  def calculateBombAmount(): Int
  def revealValue(move: Coordinates): IField
  def undo: IField
  def redo: IField
  def noStep(move: Coordinates): IField
  def setBombs(bombAmount: Int): IField
  def setFlag(coordinates: Coordinates): IField
  def field: IField
