package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.IController
import de.htwg.se.minesweeper.model.{Coordinates, Field, Stone}
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.util.Event

import scala.swing.event.MouseEvent
import scala.swing.*
import scala.swing.event.*

class SwingGui(controller: IController) extends Frame with Observer:
  controller.add(this)

  title = "Minesweeper"
  preferredSize = new Dimension(600, 600)
  resizable = false
  menuBar = new MenuBar {
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
      contents += new MenuItem(Action("Undo") {
        controller.doAndPublish(controller.undo)
      })
      contents += new MenuItem(Action("Redo") {
        controller.doAndPublish(controller.redo)
      })
    }
  }

  pack()
  centerOnScreen()
  open()

  def update(e: Event): Unit = e match
    case Event.Quit => this.dispose
    case Event.Move =>
      contents = new BorderPanel {
        add(new Label("Welcome to Minesweeper"), BorderPanel.Position.North)
        add(new CellPanel(controller.field.rows, controller.field.cols), BorderPanel.Position.Center)
      }

  class CellPanel(x: Int, y: Int) extends GridPanel(x, y):
    (0 until x)
      .map(a =>
        (0 until y)
          .map(b =>
            List(controller.field.matrix.cell(a, b))
              .map(t => contents += new CellButton(a, b, t._1.toString))
          )
      )

    def button(stone: String) = new Button(stone)

  class CellButton(x: Int, y: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    reactions += { case evt @ MouseClicked(src, pt, mod, clicks, props) =>
      evt.peer.getButton match
        case 1 => controller.doAndPublish(controller.revealValue, new Coordinates(x, y))
        case 3 => controller.doAndPublish(controller.setFlag, new Coordinates(x, y))

    }
