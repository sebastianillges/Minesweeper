package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.model.{Coordinates, Field, Stone}
import de.htwg.se.minesweeper.util.{Event, Observer}

import javax.swing.ImageIcon
import scala.swing.*
import scala.swing.event.*

class SwingGui(controller: ControllerInterface) extends Frame with Observer:
  controller.add(this)

  title = "Minesweeper"
  preferredSize = new Dimension(600, 600)
  resizable = false
  peer.setLocationRelativeTo(null)
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
      contents += new MenuItem(Action("Save") {
        controller.doAndPublish(controller.save)
      })
      contents += new MenuItem(Action("Load") {
        controller.doAndPublish(controller.load)
      })
    }
    contents += new Menu("Difficulty") {
      contents += new MenuItem(Action("Easy") {
        controller.doAndPublish(controller.createNewField("1"))
      })
      contents += new MenuItem(Action("Medium") {
        controller.doAndPublish(controller.createNewField("2"))
      })
      contents += new MenuItem(Action("Hard") {
        controller.doAndPublish(controller.createNewField("3"))
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
        add(new Label("Flags left: " + controller.flagsLeft()), BorderPanel.Position.North)
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

  class CellButton(x: Int, y: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    reactions += { case evt @ MouseClicked(src, pt, mod, clicks, props) =>
      evt.peer.getButton match
        case 1 => controller.doAndPublish(controller.revealValue, new Coordinates(x, y))
        case 3 => controller.doAndPublish(controller.setFlag, new Coordinates(x, y))
    }
