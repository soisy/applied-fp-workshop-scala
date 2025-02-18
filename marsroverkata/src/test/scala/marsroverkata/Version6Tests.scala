package marsroverkata

class Version6Tests extends munit.FunSuite {

  import cats.effect.IO
  import marsroverkata.Version6._
  import scala.Console._

  // TODO: Test after test, implement: init, update and infrastructure functions

  def execute[A](commands: String)(app: => IO[A]): String = {
    import java.io.{ ByteArrayOutputStream, StringReader }

    val input = new StringReader(commands)
    val out   = new ByteArrayOutputStream
    Console.withIn(input) {
      Console.withOut(out) {
        app.unsafeRunSync()
      }
    }
    out.toString.replace("\r", "").split('\n').last
  }

  def mkResult(state: AppState, effect: Effect): (AppState, Effect) =
    (state, effect)

  test("load mission data on init".ignore) {
    val result   = init("planet", "rover")
    val expected = mkResult(Loading, LoadMission("planet", "rover"))

    assertEquals(expected, result)
  }

  test("on load mission successful".ignore) {
    val mission  = Mission(Planet(Size(5, 5), Nil), Rover(Position(0, 0), N))
    val result   = update(Loading, LoadMissionSuccessful(mission))
    val expected = mkResult(Ready(mission), AskCommands)

    assertEquals(expected, result)
  }

  test("on load mission failed".ignore) {
    val error    = InvalidObstacle("foo", "bar")
    val result   = update(Loading, LoadMissionFailed(error))
    val expected = mkResult(Failed, Ko(error))

    assertEquals(expected, result)
  }

  test("all commands executed".ignore) {
    val mission   = Mission(Planet(Size(5, 5), Nil), Rover(Position(0, 0), N))
    val result    = update(Ready(mission), CommandsReceived(List(Move(Forward), Move(Forward), Move(Forward))))
    val lastRover = Rover(Position(0, 3), N)
    val expected  = mkResult(Ready(mission.copy(rover = lastRover)), ReportCommandSequenceCompleted(lastRover))

    assertEquals(expected, result)
  }

  test("hit obstacle".ignore) {
    val mission   = Mission(Planet(Size(5, 5), List(Obstacle(Position(0, 2)))), Rover(Position(0, 0), N))
    val result    = update(Ready(mission), CommandsReceived(List(Move(Forward), Move(Forward), Move(Forward))))
    val lastRover = Rover(Position(0, 1), N)
    val expected  = mkResult(Ready(mission.copy(rover = lastRover)), ReportObstacleHit(lastRover))

    assertEquals(expected, result)
  }

  test("go to opposite angle, system test".ignore) {
    val result = execute("RBBLBRF") {
      createApplication("planet.txt", "rover.txt")
    }

    assertEquals(result, s"$GREEN[OK] 4:3:E$RESET")
  }

  test("invalid planet data, system test".ignore) {
    val result = execute("RBBLBRF") {
      createApplication("planet_invalid_data.txt", "rover.txt")
    }

    assertEquals(result, s"$RED[ERROR] InvalidPlanet(ax4,InvalidSize)$RESET")
  }

}
