package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.{Controller, ControllerInterface}
import de.htwg.se.minesweeper.model.{Field, FieldInterface, Matrix}

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

