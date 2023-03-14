package commands

import Command
import Directions
import Position

class Backwards: Command() {
    override fun getNewPosition(direction: Directions, position: Position) = position.nextIn(direction.opposite())
}