import commands.CommandFactory

class MarsRoverController(private var position: Position, var direction: Directions) {

    private val factory = CommandFactory()
    private var obstacles = mutableListOf<Position>()
    private val ERROR = "ERROR"
    private val OK = "OK"
    private val PLATEAU_SIZE = 10

    fun execute(commands: String): String {
        try {
            processCommands(commands)
        } catch (e: ObstacleFoundError) {
            return output(ERROR)
        }
        return output(OK)
    }

    private fun processCommands(commands: String) {
        createCommandList(commands).forEach { processCommand(it) }
    }

    private fun createCommandList(commands: String) = commands.map { factory.createFor(it) }

    private fun processCommand(command: Command) {
        val nextPosition = command.getNewPosition(direction, position).wrappedBy(PLATEAU_SIZE)
        checkCollisionsAt(nextPosition)
        direction = command.getNewDirection(direction)
        position = nextPosition
    }

    private fun checkCollisionsAt(nextPosition: Position) {
        if (obstacles.contains(nextPosition)) throw ObstacleFoundError()
    }

    private fun output(status: String) = "${position.x}:${position.y}:${direction.name.first()}:${status}"

    fun addObstacleAt(position: Position) {
        obstacles.add(position)
    }
}
