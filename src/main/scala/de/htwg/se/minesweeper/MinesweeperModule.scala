package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.{Controller, ControllerInterface}
import de.htwg.se.minesweeper.model.fileIoJsonImpl.FileIOJson
import de.htwg.se.minesweeper.model.fileIoXmlImpl.FileIOXml
import de.htwg.se.minesweeper.model.{Field, FieldInterface, FileIOInterface, Matrix}

class MinesweeperModuleEasy extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(new Field(8, 8)))
  }
}

class MinesweeperModuleMedium extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(new Field(16, 16)))
  }
}

class MinesweeperModuleHard extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(new Field(32, 16)))
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
