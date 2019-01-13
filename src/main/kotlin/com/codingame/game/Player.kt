package com.codingame.game

import com.codingame.game.model.Cell
import com.codingame.game.model.Item
import com.codingame.gameengine.core.AbstractMultiplayerPlayer
import com.codingame.gameengine.module.entities.Group
import com.codingame.gameengine.module.entities.Sprite

const val REACH_DISTANCE = 3
const val WALK_DISTANCE = 7
operator fun Int?.compareTo(other: Int): Int = (this ?: Int.MAX_VALUE).compareTo(other)

class Player : AbstractMultiplayerPlayer() {
  override fun getExpectedOutputLines() = 1
  lateinit var sprite:Group
  lateinit var itemSprite: ItemSpriteGroup
  lateinit var charaterSprite: Sprite

  fun sendInputLine(toks: List<Int>) = sendInputLine(toks.joinToString(" "))
  fun sendInputLine(singleTok: Int) = sendInputLine(singleTok.toString())

  // Returns true if the use was successful
  fun use(cell: Cell): Boolean {
    if (!cell.isTable) throw Exception("Cannot use $cell: not a table!")
    if (cell.distanceTo(location) > REACH_DISTANCE) { moveTo(cell); return false }
    val equipment = cell.equipment
    if (equipment != null) {
      equipment.use(this)
      return true
    }

    // try drop
    if (heldItem != null) {
      cell.item?.also { it.receiveItem(this, heldItem!!, cell); return true }
      cell.item = heldItem
      heldItem = null
      return true
    }

    // try take
    cell.item?.also { it.take(this, cell); return true }

    throw Exception("Cannot use this table, nothing to do!")
  }

  fun moveTo(cell: Cell) {
    val fromSource = location.buildDistanceMap()
    val target =
        if (!cell.isTable)
          cell
        else
          cell.neighbours.map { it.first }
              .filter { !it.isTable }
              .filter { it in fromSource.keys }
              .minBy { fromSource[it]!! } ?: throw Exception("Cannot move from $location to table $cell; no available neighbour!")

    if (target !in fromSource.keys) throw Exception("Cannot move: no path!")

    if (location.distanceTo(target) <= WALK_DISTANCE) {
      location = target
      return
    }

    val fromTarget = target.buildDistanceMap()

    location = fromSource
        .filter { (cell, dist) -> dist <= WALK_DISTANCE && !cell.isTable }
        .minBy { (cell, _) -> fromTarget[cell]!! }!!
        .key
  }

  var heldItem: Item? = null
  lateinit var location: Cell
}
