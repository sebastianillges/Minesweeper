package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.model.{Coordinates, Field, Stone}
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
      preferredSize = new Dimension(600, 1200)
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
    if (this.stone.equals("✴")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/bomb.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/bomb.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/bomb.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/bomb.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("\u2691")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/flagged.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/flagged.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/flagged.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/flagged.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("\u25A0")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/facingDown.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/facingDown.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/facingDown.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/facingDown.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("\u25A1")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/0.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/0.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/0.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/0.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("1")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/1.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/1.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/1.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/1.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("2")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/2.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/2.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/2.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/2.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("3")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/3.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/3.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/3.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/3.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("4")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/4.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/4.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/4.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/4.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("5")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/5.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/5.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/5.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/5.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("6")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/6.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/6.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/6.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/6.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("7")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/7.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/7.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/7.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/7.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)
    else if (this.stone.equals("8")) then
      this.peer.setText("")
      var buttonIcon: BufferedImage = ImageIO.read(new File("iconsEasy/8.png"))
      controller.field.rows match {
        case 8  => buttonIcon = ImageIO.read(new File("iconsEasy/8.png"))
        case 16 => buttonIcon = ImageIO.read(new File("iconsMedium/8.png"))
        case 32 => buttonIcon = ImageIO.read(new File("iconsHard/8.png")); preferredSize.setSize(600, 1200);
      }
      buttonIcon.getScaledInstance(10, 10, 10)
      this.peer.setIcon(new ImageIcon(buttonIcon))
      this.peer.setContentAreaFilled(false)

    listenTo(mouse.clicks)
    reactions += { case evt @ MouseClicked(src, pt, mod, clicks, props) =>
      evt.peer.getButton match
        case 1 => controller.doAndPublish(controller.revealValue, new Coordinates(x, y))
        case 3 => controller.doAndPublish(controller.setFlag, new Coordinates(x, y))
    }
