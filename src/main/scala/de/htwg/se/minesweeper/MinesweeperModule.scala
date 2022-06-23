package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.{Controller, ControllerInterface}
import de.htwg.se.minesweeper.model.fileIoJsonImpl.FileIOJson
import de.htwg.se.minesweeper.model.fileIoXmlImpl.FileIOXml
import de.htwg.se.minesweeper.model.{Field, FieldInterface, FileIOInterface, Matrix}
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
