package firststeps

import javax.smartcardio.Card

/*
 * In OOP model object that encapsulate data and expose behaviours.
 * This two concepts are brigs together thanks to class definitions.
 *
 * In FP data and behaviours are modelled with two different tools:
 * - Algebraic Data Type (ADT) to model data
 * - Function to model behaviours
 *
 * An ADT is an immutable data structure that compose other types.
 * There are two common kinds of composition strategy:
 * - Product type: put many types together. e.g. struct in C, POJO in JAVA.
 *                 It's called product because all the possible values of a Tuple[String, Int] is
 *                 the *product* of all possible String with all possible Int.
 *                 Useful to model independent data in AND e.g. a Person is composed by a name *and* an age.
 *
 * - Sum type:     model exclusive types e.g. union in C, enum in JAVA.
 *                 Sum types correspond to disjoint unions of sets.
 *                 It's called sum because all the possible values of a Either[String, Int] is
 *                 the *sum* of all possible String with all possible Int.
 *                 Useful to model dependant data in OR e.g. the Light is on *or* off.
 *
 * We can mix ADT as we want, like a product type that compose a type with a sum type.
 */

class ModelData extends munit.FunSuite {
  // Proper type
  // es: Int, String

  // Type alias
  type Name = String

  // Type wrapper
  case class Age(value: Int)

  // Product type
  case class Person(name: String, age: Int)

  // Sum type
  sealed trait LightState
  case class On()  extends LightState
  case class Off() extends LightState

  sealed trait Suit
  case class Swords() extends Suit
  case class Clubs()  extends Suit
  case class Cups()   extends Suit
  case class Golds()  extends Suit

  case class Card(number: Int, seme: Suit)

  case class Player(hand: Hand)
  case class Table(card1: Card, card2: Card, card3: Card, card4: Card)
  case class Hand(card1: Card, card2: Card, card3: Card)
  case class Deck(card1: Card, card2: Card)
  case class Game(player1: Player, player2: Player, deck: Deck, table: Table)

  sealed trait TableStatus
  case class NoCard()                                                      extends TableStatus
  case class FourCards(card1: Card, card2: Card, card3: Card, card4: Card) extends TableStatus

  /*
   * TODO: Model "Scopa" the italian card game, below the game description. :-)
   *       After modeling the domain implements the test.
   *
   * DESCRIPTION:
   *       It is played (let simplify) between two players with
   *       a standard Italian 40-card deck, divided into four suits: Cups, Golds, Swords, Clubs.
   *       The values on the cards range numerically from one through seven,
   *       plus three face cards in each suit: Knight (worth 8), Queen (worth 9) and King (worth 10).
   *       Each player receives three cards. The dealer will also place four cards face up on the table.
   */

  test("represent initial match state") {
    val player1 = Player(
      Hand(
        Card(2, Golds()),
        Card(5, Swords()),
        Card(7, Clubs())
      )
    )

    val player2 = Player(
      Hand(
        Card(1, Cups()),
        Card(2, Clubs()),
        Card(9, Golds())
      )
    )

    val table = Table(
      Card(4, Clubs()),
      Card(10, Swords()),
      Card(8, Golds()),
      Card(1, Swords())
    )

    val deck  = Deck(Card(1, Swords()), Card(10, Clubs()))
    val game = Game(player1, player1, deck, table)

    // TODO: build the first player w/ 2 of Golds, 5 of Swords and 7 of Clubs
    // TODO: build the second player w/ 1 of Cups, 2 of Clubs and 9 of Golds
    // TODO: build the table w/ 4 of Clubs, 10 of Swords, 8 of Golds and 1 of Swords
    // TODO: build the deck w/ only 1 of Swords and 10 of Clubs
    // TODO: build the game
  }
}
