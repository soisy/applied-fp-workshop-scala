package marsroverkata

class Version1Tests extends munit.FunSuite {

  import marsroverkata.Version1._

// +-----+-----+-----+-----+-----+
// | 0,3 |     |     |     | 4,3 |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// | 0,0 |     |     |     | 4,0 |
// +-----+-----+-----+-----+-----+

  // test("turn right command") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 0 N
  //       Command: R
  //       --
  //       Rover: 0 0 E
  //    */
  //   assertEquals(
  //     Rover(Position(0, 0), N()).move(R()), 
  //     Rover(Position(0, 0), E())
  //     )
  // }

  // test("turn left command") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 0 N
  //       Command: L
  //       --
  //       Rover: 0 0 W
  //    */
  //   assertEquals(
  //     Rover(Position(0, 0), N()).move(L()), 
  //     Rover(Position(0, 0), W())
  //     )
  // }

  // test("move forward command") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 1 N
  //       Command: F
  //       --
  //       Rover: 0 2 N
  //    */
  //   assertEquals(
  //     Rover(Position(0, 1), N()).move(F()), 
  //     Rover(Position(0, 2), N())
  //     )
  // }

  // test("move forward command, opposite orientation") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 1 S
  //       Command: F
  //       --
  //       Rover: 0 0 S
  //    */
  //   assertEquals(
  //     Rover(Position(0, 1), S()).move(F()), 
  //     Rover(Position(0, 0), S())
  //     )
  // }

  // test("move backward command") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 1 N
  //       Command: B
  //       --
  //       Rover: 0 0 N
  //    */
  //   assertEquals(
  //     Rover(Position(0, 1), N()).move(B()), 
  //     Rover(Position(0, 0), N())
  //     )
  // }

  // test("move backward command, opposite orientation") {
  //   /*
  //       Planet: 5 4
  //       Rover: 0 1 S
  //       Command: B
  //       --
  //       Rover: 0 2 S
  //    */
  //   assertEquals(
  //     Rover(Position(0, 1), S()).move(B()), 
  //     Rover(Position(0, 2), S())
  //     )
  // }

  test("wrap on North") {
    /*
        Planet: 5 4
        Rover: 0 3 N
        Command: F
        --
        Rover: 0 0 N
     */
    val planet = Planet(5, 4)
    assertEquals(
      Rover(Position(0, 3), N(), planet).move(F()), 
      Rover(Position(0, 0), N(), planet)
      )
  }

  test("wrap on South") {
    /*
        Planet: 5 4
        Rover: 0 0 S
        Command: F
        --
        Rover: 0 3 S
     */
  }

  test("wrap on Est") {
    /*
        Planet: 5 4
        Rover: 4 1 E
        Command: F
        --
        Rover: 0 1 E
     */
  }

  test("wrap on West") {
    /*
        Planet: 5 4
        Rover: 0 1 W
        Command: F
        --
        Rover: 4 1 W
     */
  }
}