package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import de.htwg.se.minesweeper.controller.{Controller, ControllerInterface}
import de.htwg.se.minesweeper.model.{Field, FieldInterface, Matrix}

class MinesweeperModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[FieldInterface]).to(classOf[Field])
    bind(classOf[ControllerInterface]).to(classOf[Controller])
  }
}
