package com.codingame.game

import com.codingame.game.model.*
import com.codingame.gameengine.core.AbstractReferee
import com.codingame.gameengine.core.MultiplayerGameManager
import com.codingame.gameengine.module.entities.GraphicEntityModule
import com.codingame.gameengine.module.entities.Rectangle
import com.codingame.gameengine.module.entities.Text
import com.google.inject.Inject

@Suppress("unused")  // injected by magic
class Referee : AbstractReferee() {
  @Inject
  private lateinit var gameManager: MultiplayerGameManager<Player>
  @Inject
  private lateinit var graphicEntityModule: GraphicEntityModule

  private lateinit var board: Board
  private lateinit var queue: CustomerQueue

  private var scores = mutableMapOf<Int, Text>()

  private var cellWidth: Int = 0

  val edibleEncoding: Map<EdibleItem, Int> = mapOf(
      IceCreamBall(IceCreamFlavour.VANILLA) to Constants.VANILLA_BALL,
      IceCreamBall(IceCreamFlavour.CHOCOLATE) to Constants.CHOCOLATE_BALL,
      IceCreamBall(IceCreamFlavour.BUTTERSCOTCH) to Constants.BUTTERSCOTCH_BALL,
      Strawberries to Constants.STRAWBERRIES,
      Blueberries to Constants.BLUEBERRIES,
      ChoppedBananas to Constants.CHOPPED_BANANAS,
      PieSlice(PieFlavour.Strawberry) to Constants.STRAWBERRY_PIE,
      PieSlice(PieFlavour.Blueberry) to Constants.BLUEBERRY_PIE,
      Waffle to Constants.WAFFLE
  )

  private val equipmentColoring: Map<Int, Int> = mapOf(
      Constants.EQUIPMENT.BANANA_CRATE to 0xffffff,
      Constants.EQUIPMENT.BLENDER to 0x000000,
      Constants.EQUIPMENT.BLUEBERRY_CRATE to 0xffffff,
      Constants.EQUIPMENT.BUTTERSCOTCH_CRATE to 0xDEC319,
      Constants.EQUIPMENT.CHOCOLATE_CRATE to 0x3F1111,
      Constants.EQUIPMENT.CHOPPINGBOARD to 0xffffff,
      Constants.EQUIPMENT.DISH_RETURN to 0x939393,
      Constants.EQUIPMENT.OVEN to 0xffffff,
      Constants.EQUIPMENT.PIECRUST_CRATE to 0xffffff,
      Constants.EQUIPMENT.STRAWBERRY_CRATE to 0xffffff,
      Constants.EQUIPMENT.VANILLA_CRATE to 0xF7F7F7,
      Constants.EQUIPMENT.WAFFLEIRON to 0xffffff,
      Constants.EQUIPMENT.WINDOW to 0x0C428C
  ).mapKeys { it.key.ordinal }

  private fun getEquipmentColor(equipmentId: Int): Int = when (equipmentId) {
    in equipmentColoring.keys -> equipmentColoring[equipmentId]!!
    else -> 0x000000
  }

  private fun Item?.describe(): Int = when (this) {
    is Dish -> Constants.DISH + contents.map { it.describe() }.sum()
    is Milkshake -> Constants.MILKSHAKE + contents.map { it.describe() }.sum()
    in edibleEncoding.keys -> edibleEncoding[this]!!
    else -> -1
  }


  override fun init() {
    fun teamMap() =
        when (gameManager.activePlayers.size) {
          2 -> mapOf(0 to listOf(0), 1 to listOf(1))
          4 -> mapOf(1 to listOf(0, 3), 0 to listOf(1, 2))
          else -> throw Exception("Expected 2 or 4 players!")
        }

    fun awardTeamPoints(teamIndex: Int, points: Int) {
      println("Team $teamIndex gets $points points")
      teamMap()[teamIndex]!!
          .forEach {
            gameManager.players[it].score += points
            scores[gameManager.players[it].colorToken]!!.text = gameManager.players[it].score.toString()
          }
    }

    val (b, q) = buildBoardAndQueue(::awardTeamPoints)
    board = b
    queue = q

    fun printRect(rect: Rectangle) {
      println("${rect.width} ${rect.height} ${rect.x} ${rect.y}")
    }

    gameManager.activePlayers[0].apply { isLeftTeam = true; location = board["b3"] }
    gameManager.activePlayers[1].apply { isLeftTeam = false; location = board["B3"] }
    gameManager.activePlayers[2].apply { isLeftTeam = false; location = board["B5"] }
    gameManager.activePlayers[3].apply { isLeftTeam = true; location = board["b5"] }

    scores[gameManager.activePlayers[0].colorToken] = graphicEntityModule.createText("0").setX(0).setY(10).setFillColor(gameManager.activePlayers[0].colorToken)
    scores[gameManager.activePlayers[3].colorToken] = graphicEntityModule.createText("0").setX(200).setY(10).setFillColor(gameManager.activePlayers[3].colorToken)
    scores[gameManager.activePlayers[1].colorToken] = graphicEntityModule.createText("0").setX(400).setY(10).setFillColor(gameManager.activePlayers[1].colorToken)
    scores[gameManager.activePlayers[2].colorToken] = graphicEntityModule.createText("0").setX(600).setY(10).setFillColor(gameManager.activePlayers[2].colorToken)

    var fill = 0xeeeeee
    var tableFill = 0x8B4513

    val worldWidth = 1920
    val worldHeight = 1080

    val cellSpacing = 5
    val yOffset = 100
    val xOffset = 200
    val gridHeight = worldHeight - yOffset
    val gridWidth = worldWidth - xOffset
    cellWidth = Math.min(gridHeight / board.height, gridWidth / board.width) - cellSpacing

    for (cellCol in board.cells) {

      for (cell in cellCol) {
        val x = (cell.x + board.width - 1) * (cellWidth + cellSpacing) + xOffset / 2

        val y = cell.y * (cellWidth + cellSpacing) + yOffset
//        println("$x-$y")
//        println("$cellWidth $x $y")

        var fillColor = fill //start with floor color

        if (cell.isTable) {
          fillColor = tableFill
        }

        if (cell.equipment !== null) {
          fillColor = getEquipmentColor(cell.equipment?.describeAsNumber()!!)
        }

        cell.visualRect = graphicEntityModule
            .createRectangle()
            .setHeight(cellWidth)
            .setWidth(cellWidth)
            .setFillColor(fillColor)

        cell.visualContent = graphicEntityModule.createText(if (cell.item.describe() > 0) cell.item.describe().toString() else "")
        cell.sprite = graphicEntityModule.createGroup(cell.visualRect, cell.visualContent).setX(x).setY(y)
      }
    }

    for (player in gameManager.activePlayers) {
      player.charaterSprite = graphicEntityModule.createRectangle()
          .setHeight(cellWidth - 10)
          .setWidth(cellWidth - 10)
          .setFillColor(player.colorToken)

      player.itemSprite = graphicEntityModule.createText("0").setAlpha(0.0)

      player.sprite = graphicEntityModule.createGroup(player.charaterSprite, player.itemSprite)
          .setX(player.location.sprite.x + 5)
          .setY(player.location.sprite.y + 5)
    }

    fun describeMap(player: Player) {
      // Describe the table cells on YOUR HALF of the board (width, height, location of equipment)

      player.sendInputLine("${board.width} ${board.height}")
      board.allCells
          .filter { it.x >= 0 }
          .filter { it.isTable }
          .also { player.sendInputLine(it.size.toString()) }
          .also {
            it.forEach { cell ->
              player.sendInputLine("${cell.x} ${cell.y} ${cell.equipment?.describeAsNumber() ?: -1}")
            }
          }
    }

    gameManager.activePlayers.forEach(::describeMap)
  }

  override fun gameTurn(turn: Int) {
    board.tick()
    queue.tick()

    fun sendGameState(player: Player) {
      val xMult = if (player.isLeftTeam) -1 else 1  // make sure to invert x for left team

      // 1. Describe all players, including self
      gameManager.activePlayers.forEach {

        val toks = listOf(
            it.location.x * xMult,
            it.location.y,
            it.heldItem.describe(),
            when {
              it == player -> 0    // self
              it.isLeftTeam == player.isLeftTeam -> 1   // friend
              else -> 2    // enemy
            }
        )
        player.sendInputLine(toks)
      }

      // 2. Describe all table cells
      board.allCells.filter { it.isTable }
          .also { player.sendInputLine(it.size) }
          .forEach {
            val toks = listOf(
                it.x * xMult,
                it.y,
                it.equipment?.describeAsNumber() ?: -1,
                it.item.describe()
            )
            player.sendInputLine(toks)

            it.visualContent.text = if (it.item.describe() > 0) it.item.describe().toString() else ""
          }

      // 3. Describe customer queue
      queue
          .also { player.sendInputLine(it.size) }
          .also {
            it.forEach {
              val toks = listOf(it.award) + it.item.describe()
              player.sendInputLine(toks)
            }
          }

      player.execute()
    }

    fun processPlayerActions(player: Player) {
      val xMult = if (player.isLeftTeam) -1 else 1  // make sure to invert x for left team

      val line = player.outputs[0].trim()
      val toks = line.split(" ").iterator()
      val command = toks.next()

      if (command != "WAIT") {
        val cellx = toks.next().toInt() * xMult
        val celly = toks.next().toInt()
        val target = board[cellx, celly]

        when (command) {
          "MOVE" -> player.moveTo(target)
          "USE" -> player.use(target)
        }
      }

      if (player.heldItem != null) {
        player.itemSprite.setText(player.heldItem.describe().toString()).setAlpha(1.0)
      } else {
        player.itemSprite.setAlpha(0.0)
      }

      player.sprite
          .setX(board[player.location.x, player.location.y].sprite.x + 5)
          .setY(board[player.location.x, player.location.y].sprite.y + 5)

      graphicEntityModule.commitEntityState(0.5, player.sprite)
    }

    val thePlayers =
        when (gameManager.playerCount) {
          2 -> gameManager.activePlayers
          4 -> if (turn % 2 == 0) gameManager.activePlayers.take(2) else gameManager.activePlayers.takeLast(2)
          else -> throw Exception("Expected 2 or 4 players!")
        }

    thePlayers.forEach(::sendGameState)
    thePlayers.forEachIndexed { index, it ->
      try {
        processPlayerActions(it)
      } catch (ex: Exception) {
        System.err.println("${it.nicknameToken}: $ex")
      }
    }
  }
}



