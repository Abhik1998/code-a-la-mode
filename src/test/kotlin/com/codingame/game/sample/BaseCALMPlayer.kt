package sample

import java.io.InputStream
import java.io.PrintStream
import java.util.*

@Suppress("unused")
abstract class BaseCALMPlayer(val stdin: InputStream, val stdout: PrintStream, val stderr: PrintStream) {
  val scanner = Scanner(stdin)
  val width: Int = 11
  val height: Int = 7
  val layout: List<String>

  init {
    val longQueue = List(scanner.nextInt()) {
      Customer(Item.parse(scanner.next())!!, scanner.nextInt())
    }

    layout = List(height) { scanner.next() }
  }

  protected fun readInputs(): GameState {
//    scanner.next().also { stderr.println("Read: $it, expecting number of turns")}
    val turnsRemaining = scanner.nextInt()
    val myPlayer = Player(scanner.nextInt(), scanner.nextInt(), Item.parse(scanner.next()))
    val myFriend = Player(scanner.nextInt(), scanner.nextInt(), Item.parse(scanner.next()))

    val tables = (0 until height).flatMap { row -> (0 until width)
        .filter { col -> layout[row][col] != '.'}
        .map { col -> Table(col, row, layout[row][col].nullIf('#')) }
    }

    repeat(scanner.nextInt()
        .also { stderr.println("$it tables with items")}
    ) {
      val x = scanner.nextInt()
      val y = scanner.nextInt()
      tables.find { it.x == x && it.y == y }!!.item = Item.parse(scanner.next()).also {
        stderr.println("Setting table $x $y to $it")
      }
    }

    val ovenContents = scanner.next()
    val ovenTimer = scanner.nextInt()

    val queue = List(scanner.nextInt()) {
      Customer(Item.parse(scanner.next())!!, scanner.nextInt())
    }

    return GameState(myPlayer, myFriend, tables, queue, ovenContents, ovenTimer)
  }
}

private fun Char.nullIf(char: Char) = if (this == char) null else this

data class GameState(
    val myPlayer: Player,
    val myFriend: Player,
    val tables: List<Table>,
    val queue: List<Customer>,
    val ovenContents: String,
    val ovenTimer: Int)

data class Table(
    val x: Int, val y: Int,
    var equipment: Char? = null,
    var item: Item? = null)

data class Item(val description: String) {
  val toks = description.split("-")
  val itemType = toks[0]
  val itemContents = toks.drop(1)

  companion object {
    fun parse(description: String) =
        if (description == "NONE") null
        else Item(description)

  }
}

data class Player(val x: Int, val y: Int, val carrying: Item?)
data class Customer(val dish: Item, val award: Int)