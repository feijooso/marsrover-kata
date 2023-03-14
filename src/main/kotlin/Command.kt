abstract class Command {
    open fun getNewPosition(direction: Directions, position: Position) = position
    open fun getNewDirection(direction: Directions) = direction
}
