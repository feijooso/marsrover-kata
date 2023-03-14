package commands

import Command
import Directions

class Left: Command() {
    override fun getNewDirection(direction: Directions) = direction.left()
}