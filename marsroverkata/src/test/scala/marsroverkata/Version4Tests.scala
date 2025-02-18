package marsroverkata

class Version4Tests extends munit.FunSuite {

  import scala.util._
  import cats.implicits._
  import cats.effect._

  import marsroverkata.Version4._

// +-----+-----+-----+-----+-----+
// | 0,3 |     |     |     | 4,3 |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// | 0,0 |     |     |     | 4,0 |
// +-----+-----+-----+-----+-----+

  test("load planet data (integration test with real filesystem)") {
    // TODO: all the code for this test has been implemented.
    //  Take a look to the loadPlanetData implementation.

    val load   = loadPlanetData("planet.txt")
    val result = load.unsafeRunSync()
    assertEquals(result, ("5x4", "2,0 0,3 3,2"))
  }

  test("ask commands (integration test with real console)") {
    // TODO: all the code for this test has been implemented.
    //  Take a look to the askCommands implementation.

    def execute(commands: String): String = {
      import java.io.ByteArrayOutputStream
      import java.io.StringReader

      val input = new StringReader(commands)
      val out   = new ByteArrayOutputStream
      Console.withIn(input) {
        Console.withOut(out) {
          askCommands().unsafeRunSync()
        }
      }
      out.toString.replace("\r", "")
    }

    val result = execute("RRF")

    assertEquals(result, "Waiting commands...\n")
  }

  test("go to opposite angle, system test (with real infrastructure)".ignore) {
    // TODO: complete the test

    def execute[A](commands: String)(app: => IO[A]): A = {
      import java.io.ByteArrayOutputStream
      import java.io.StringReader

      val input = new StringReader(commands)
      val out   = new ByteArrayOutputStream
      Console.withIn(input) {
        Console.withOut(out) {
          app.unsafeRunSync()
        }
      }
    }

    val result: Either[Error, String] = execute("RBBLBRF") {
      // val planet   = load planet data...
      // val rover    = load rover data...
      // val commands = ask commands data...
      // lift domain domain entry point: planet, rover and commands
      ???
    }

    assertEquals(result, Right("4:3:E"))
  }

  test("go to opposite angle, stubbed".ignore) {
    // TODO: complete the test

    // val planet   = lift ("5x4", "2,0 0,3 3,2") into IO
    // val rover    = lift ("0,0", "N") into IO
    // val commands = lift "RBBLBRF" into IO
    // lift domain entry point with: planet, rover and commands
    val app: IO[Either[Error, String]] = ???

    val result = app.unsafeRunSync()

    assertEquals(result, Right("4:3:E"))
  }

  test("unhandled RuntimeException".ignore) {
    // TODO: complete the test

    // val planet   = lift ("5x4", "2,0 0,3 3,2") into IO
    // val rover    = lift RuntimeException("boom!") into IO
    // val commands = lift "RBBLBRF" into IO
    // val app = lift domain domain entry point: planet, rover and commands...
    val app: IO[Either[Error, String]] = ???

    val ex = intercept[Exception](app.unsafeRunSync())

    assertEquals("boom!", ex.getMessage)
  }

  test("handled RuntimeException".ignore) {
    // TODO: complete the test

    // val planet   = lift ("5x4", "2,0 0,3 3,2") into IO
    // val rover    = lift RuntimeException("boom!") into IO
    // val commands = lift "RBBLBRF" into IO
    // val app = lift domain domain entry point: planet, rover and commands...
    val app: IO[Either[Error, String]] = ???

    val result = app.attempt.unsafeRunSync()

    assert(result.isLeft)
  }
}
