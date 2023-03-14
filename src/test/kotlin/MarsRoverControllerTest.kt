import Directions.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MarsRoverControllerTest {

    @ParameterizedTest
    @CsvSource(
        "0, 0, 0:1:N:OK",
        "0, 1, 0:2:N:OK",
        "0, 2, 0:3:N:OK",
    )
    fun `moving forward facing north increments y position`(initialX: Int, initialY: Int, expectedOutput: String) {
        val marsRover = createController(initialX, initialY, North)

        val output = marsRover.execute("F")

        assertThat(output).isEqualTo(expectedOutput)
    }

    @Test
    fun `moving backwards facing north decrements y position`() {
        val marsRover = createController(0, 1, North)

        val output = marsRover.execute("B")

        assertThat(output).isEqualTo("0:0:N:OK")
    }

    @Test
    fun `moving forward facing south decrements y position`() {
        val marsRover = createController(0, 1, South)

        val output = marsRover.execute("F")

        assertThat(output).isEqualTo("0:0:S:OK")
    }

    @Test
    fun `moving backwards facing south increments y position`() {
        val marsRover = createController(0, 0, South)

        val output = marsRover.execute("B")

        assertThat(output).isEqualTo("0:1:S:OK")
    }

    @Test
    fun `moving forward facing east increments x position`() {
        val marsRover = createController(0, 0, East)

        val output = marsRover.execute("F")

        assertThat(output).isEqualTo("1:0:E:OK")
    }

    @Test
    fun `moving backwards facing east decrements x position`() {
        val marsRover = createController(1, 0, East)

        val output = marsRover.execute("B")

        assertThat(output).isEqualTo("0:0:E:OK")
    }

    @Test
    fun `moving forward facing west decrements x position`() {
        val marsRover = createController(1, 0, West)

        val output = marsRover.execute("F")

        assertThat(output).isEqualTo("0:0:W:OK")
    }

    @Test
    fun `moving backwards facing west increments x position`() {
        val marsRover = createController(0, 0, West)

        val output = marsRover.execute("B")

        assertThat(output).isEqualTo("1:0:W:OK")
    }

    @Test
    fun `turning right facing north changes direction to east`() {
        val marsRover = createController(0, 0, North)

        val output = marsRover.execute("R")

        assertThat(output).isEqualTo("0:0:E:OK")
    }

    @Test
    fun `turning right facing south changes direction to west`() {
        val marsRover = createController(0, 0, South)

        val output = marsRover.execute("R")

        assertThat(output).isEqualTo("0:0:W:OK")
    }

    @Test
    fun `turning right facing east changes direction to south`() {
        val marsRover = createController(0, 0, East)

        val output = marsRover.execute("R")

        assertThat(output).isEqualTo("0:0:S:OK")
    }

    @Test
    fun `turning right facing west changes direction to north`() {
        val marsRover = createController(0, 0, West)

        val output = marsRover.execute("R")

        assertThat(output).isEqualTo("0:0:N:OK")
    }

    @Test
    fun `turning left facing north changes direction to west`() {
        val marsRover = createController(0, 0, North)

        val output = marsRover.execute("L")

        assertThat(output).isEqualTo("0:0:W:OK")
    }

    @Test
    fun `turning left facing south changes direction to east`() {
        val marsRover = createController(0, 0, South)

        val output = marsRover.execute("L")

        assertThat(output).isEqualTo("0:0:E:OK")
    }

    @Test
    fun `turning left facing east changes direction to north`() {
        val marsRover = createController(0, 0, East)

        val output = marsRover.execute("L")

        assertThat(output).isEqualTo("0:0:N:OK")
    }

    @Test
    fun `turning left facing west changes direction to south`() {
        val marsRover = createController(0, 0, West)

        val output = marsRover.execute("L")

        assertThat(output).isEqualTo("0:0:S:OK")
    }

    @ParameterizedTest
    @CsvSource(
        "FFFF, 0:4:N:OK",
        "FBFF, 0:2:N:OK",
    )
    fun `moving several steps in one direction changes position`(command: String, expectedOutput: String) {
        val marsRover = createController(0, 0, North)

        val output = marsRover.execute(command)

        assertThat(output).isEqualTo(expectedOutput)
    }

    @Test
    fun `moving and turning changes position in several directions`() {
        val marsRover = createController(0, 0, North)

        val output = marsRover.execute("FFRFFLF")

        assertThat(output).isEqualTo("2:3:N:OK")
    }

    @Test
    fun `moving when there is an obstacle gives error message`() {
        val marsRover = createController(0, 0, North)
        marsRover.addObstacleAt(Position(0, 1))

        val output = marsRover.execute("F")

        assertThat(output).isEqualTo("0:0:N:ERROR")
    }

    @Test
    fun `colliding with an obstacle stops the command execution`() {
        val marsRover = createController(0, 0, North)
        marsRover.addObstacleAt(Position(0, 2))

        val output = marsRover.execute("FFF")

        assertThat(output).isEqualTo("0:1:N:ERROR")
    }

    @Test
    fun `edges are wrapped`() {
        val marsRover = createController(0, 0, North)

        val output = marsRover.execute("FFFFFFFFFF")

        assertThat(output).isEqualTo("0:0:N:OK")
    }

    private fun createController(initialX: Int, initialY: Int, directions: Directions) =
        MarsRoverController(Position(initialX, initialY), directions)

}
