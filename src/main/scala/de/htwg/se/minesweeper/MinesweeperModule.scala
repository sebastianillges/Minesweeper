package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.controllerComponent.*
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.FieldBaseImpl.*
import de.htwg.se.minesweeper.model.FileIOComponent.*
import de.htwg.se.minesweeper.model.FileIOComponent.fileIoXmlImpl.*
import de.htwg.se.minesweeper.model.FileIOComponent.fileIoJsonImpl.*
import de.htwg.se.minesweeper.util.DifficultyFactory

class MinesweeperModuleEasy extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(DifficultyFactory("1").run))
  }
}

class MinesweeperModuleMedium extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(DifficultyFactory("2").run))
  }
}

class MinesweeperModuleHard extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(DifficultyFactory("3").run))
  }
}

class MinesweeperXML extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[FileIOInterface]).toInstance(new FileIOXml())
  }
}

class MinesweeperJson extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[FileIOInterface]).toInstance(new FileIOJson())
  }
}
