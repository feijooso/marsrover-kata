import Directions.*

class Position(val x: Int, val y: Int) {

    override fun equals(other: Any?) = other is Position && other.x == x && other.y == y

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString() = "Position($x, $y)"

    fun nextIn(direction: Directions): Position {
        return when (direction) {
            North -> Position(x, y + 1)
            South -> Position(x, y - 1)
            East -> Position(x + 1, y)
            West -> Position(x - 1, y)
        }
    }

    fun wrappedBy(size: Int) = Position(x % size, y % size)
}