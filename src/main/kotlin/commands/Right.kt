package commands

import Command
import Directions

class Right: Command() {
    override fun getNewDirection(direction: Directions) = direction.right()
}