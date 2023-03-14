package commands
import InvalidCommandError

class CommandFactory {
    fun createFor(command: Char) = when (command) {
        'R' -> Right()
        'L' -> Left()
        'B' -> Backwards()
        'F' -> Forward()
        else -> throw InvalidCommandError()
    }
}
