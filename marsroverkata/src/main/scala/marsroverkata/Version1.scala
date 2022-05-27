package marsroverkata

object Version1 {
    sealed trait Orientation {
        def moveRight(): Orientation = this match {
            case E() => S()
            case S() => W()
            case W() => N()
            case N() => E()
        }
        def moveLeft(): Orientation = this match {
            case E() => N()
            case N() => W()
            case W() => S()
            case S() => E()
        }
    }
    case class E() extends Orientation
    case class N() extends Orientation
    case class W() extends Orientation
    case class S() extends Orientation

    case class Position(x: Int, y: Int) {
        def moveForward(orientation: Orientation): Position = orientation match {
            case N() => Position(x, y + 1)
            case S() => Position(x, y - 1)
            case E() => Position(x + 1, y)
            case W() => Position(x - 1, y)
        }

        def moveBackword(orientation: Orientation): Position = orientation match {
            case N() => Position(x, y - 1)
            case S() => Position(x, y + 1)
            case E() => Position(x - 1, y)
            case W() => Position(x + 1, y)
        }
    }
    
    case class Planet(width: Int, height: Int) {
       def inPlanet(position: Position): Position = 
           position match {
               case Position(-1, position.y) => Position(width-1, position.y) 
               case Position(position.x, -1) => Position(position.x, height-1) 
               case Position(width, position.y) => Position(0, position.y) 
               case Position(position.x, height) => Position(position.x, 0) 
               case _ => position
           }
        }

    sealed trait Command
    case class R() extends Command
    case class L() extends Command
    case class F() extends Command
    case class B() extends Command

    case class Rover(position: Position, orientation: Orientation, planet: Planet) {
        def move(command: Command): Rover = 
            command match {
                case R() => copy(orientation = orientation.moveRight())
                case L() => copy(orientation = orientation.moveLeft())
                case F() => Rover(planet.inPlanet(position.moveForward(orientation)), orientation, planet)
                case B() => Rover(planet.inPlanet(position.moveBackword(orientation)), orientation, planet)
            }
    }
}