package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.{Coordinates, Field, Stone}
import de.htwg.se.minesweeper.util.{Event, Observer}

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.{BorderFactory, ImageIcon}
import javax.swing.border.EmptyBorder
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

  def update(e: Event): Unit =
    if (controller.field.rows == 32)
      preferredSize = new Dimension(440, 880)
    else
      preferredSize = new Dimension(600, 600)
    e match
      case Event.Quit => this.dispose
      case Event.Move =>
        contents = new BorderPanel {
          add(new Label("Flags left: " + controller.flagsLeft()), BorderPanel.Position.North)
          add(new CellPanel(controller.field.rows, controller.field.cols), BorderPanel.Position.Center)
        }

  class CellPanel(x: Int, y: Int) extends GridPanel(x, y):
    var i = 0
    (0 until x)
      .map(a =>
        (0 until y)
          .map(b =>
            List(controller.field.matrix.cell(a, b))
              .map(t => contents += new CellButton(a, b, t._1.toString))
          )
      )

  class CellButton(x: Int, y: Int, var stone: String) extends Button(stone):
    this.peer.setText("")
    var buttonIcon: BufferedImage = ImageIO.read(new File("src/main/resources/iconsEasy/bomb.png"))
    buttonIcon.getScaledInstance(10, 10, 10)
    if (this.stone.equals("✴")) then
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("src/main/resources/iconsEasy/bomb.png"))
        case 16 => buttonIcon = ImageIO.read(new File("src/main/resources/iconsMedium/bomb.png"))
        case 32 =>
          buttonIcon = ImageIO.read(new File("src/main/resources/iconsHard/bomb.png"));
      }
      this.peer.setIcon(new ImageIcon(buttonIcon))
    else if (this.stone.equals("\u2691")) then
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("src/main/resources/iconsEasy/flagged.png"))
        case 16 => buttonIcon = ImageIO.read(new File("src/main/resources/iconsMedium/flagged.png"))
        case 32 =>
          buttonIcon = ImageIO.read(new File("src/main/resources/iconsHard/flagged.png"));
      }
      this.peer.setIcon(new ImageIcon(buttonIcon))
    else if (this.stone.equals("\u25A0")) then
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("src/main/resources/iconsEasy/facingDown.png"))
        case 16 => buttonIcon = ImageIO.read(new File("src/main/resources/iconsMedium/facingDown.png"))
        case 32 =>
          buttonIcon = ImageIO.read(new File("src/main/resources/iconsHard/facingDown.png"));
          preferredSize.setSize(600, 1200);
      }
      this.peer.setIcon(new ImageIcon(buttonIcon))
    else if (this.stone.equals("\u25A1")) then
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("src/main/resources/iconsEasy/0.png"))
        case 16 => buttonIcon = ImageIO.read(new File("src/main/resources/iconsMedium/0.png"))
        case 32 =>
          buttonIcon = ImageIO.read(new File("src/main/resources/iconsHard/0.png"));
      }
      this.peer.setIcon(new ImageIcon(buttonIcon))
    else
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("src/main/resources/iconsEasy/" + stone + ".png"))
        case 16 => buttonIcon = ImageIO.read(new File("src/main/resources/iconsMedium/" + stone + ".png"))
        case 32 =>
          buttonIcon = ImageIO.read(new File("src/main/resources/iconsHard/" + stone + ".png"));
      }
      this.peer.setIcon(new ImageIcon(buttonIcon))

    listenTo(mouse.clicks)
    reactions += { case evt @ MouseClicked(src, pt, mod, clicks, props) =>
      evt.peer.getButton match
        case 1 => controller.doAndPublish(controller.revealValue, new Coordinates(x, y))
        case 3 => controller.doAndPublish(controller.setFlag, new Coordinates(x, y))
    }
