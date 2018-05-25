import com.codingame.game.Board
import com.codingame.game.Player
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowAny
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class BoardSpec: FreeSpec({
  "an empty board" - {
    val board = Board(  5,5)

    "neighbour distances" {
      board["B2"].distanceTo(board["B3"]).shouldBe(2)
      board["C2"].distanceTo(board["D3"]).shouldBe(3)
    }

    "longer distances" {
      board["A3"].distanceTo(board["A1"]).shouldBe(4)
      board["A0"].distanceTo(board["D4"]).shouldBe(11)
    }
  }

  "a board with obstructions" - {
    val boardLayout = listOf(
      "...X.",  // 0
      "XXXX.",  // 1
      ".X...",  // 2
      ".XX..",  // 3
      "....."   // 4
    // ABCDE    -- A is always shared with the opponent
    )
    val board = Board(5, 5, boardLayout)

    "Distances should be accurate" {
      val myTable = table(
        headers("Start", "Finish", "Distance"),
        row("B1", "B3", 6 as Int?),
        row("B1", "C3", 5 as Int?),
        row("B1", "C4", 9 as Int?),
        row("A0", "A0", 0 as Int?),
        row("B1", "B1", 0 as Int?),
        row("A0", "C2", null as Int?),
        row("A2", "E0", 17 as Int?)
      )

      fun negafyCellName(cellName: String) = ('a' + (cellName[0] - 'A')) + cellName.substring(1)

      forAll(myTable) { c1, c2, d ->
        board[c1].distanceTo(board[c2]) shouldBe d
        board[negafyCellName(c1)].distanceTo(board[negafyCellName(c2)]) shouldBe d
      }
    }

    "Player can move a max of 7 distance" {
      val moveTable = table(
        headers("Start", "Finish", "Can Move"),
        row("A4", "A1", false),
        row("A2", "C4", true)
      )

      val player = Player()

      forAll(moveTable) { start, end, canMove ->
        player.location = board[start]

        fun doit() {
          player.moveTo(board[end])
          player.location shouldBe board[end]
        }

        if (canMove) doit()
        else shouldThrowAny { doit() }
      }

    }

    "Player cannot move onto a table" {
      val moveTable = table(
              headers("Start", "Finish", "Can Move"),
              row("A4", "E4", false),
              row("A2", "C4", true)
      )
    }
  }
})