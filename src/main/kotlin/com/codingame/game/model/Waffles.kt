package com.codingame.game.model

import com.codingame.game.Player

object Waffle: EdibleItem()
object BurntWaffle: Item()

sealed class WaffleState {
  object Empty : WaffleState()
  data class Cooking(val timeUntilCooked: Int): WaffleState()
  data class Cooked(val timeUntilBurnt: Int): WaffleState()
  object Burnt: WaffleState()
}

data class WaffleIron(private val cookTime: Int, private val burnTime: Int, private var state: WaffleState = WaffleState.Empty) : TimeSensitiveEquipment() {
  override fun clone(): Equipment = copy()
  override fun describeAsNumber() = Constants.EQUIPMENT.WAFFLEIRON.ordinal

  override fun tick() {
    val curState = state
    state = when (curState) {
      is WaffleState.Empty -> return
      is WaffleState.Cooking -> {
        val time = curState.timeUntilCooked
        if (time == 1) WaffleState.Cooked(burnTime) else curState.copy(timeUntilCooked = time-1)
      }
      is WaffleState.Cooked -> {
        val time = curState.timeUntilBurnt
        if (time == 1) WaffleState.Burnt else curState.copy(timeUntilBurnt = time-1)
      }
      is WaffleState.Burnt -> return
    }
  }

  override fun takeFrom(player: Player): Item {
    lateinit var retVal: Item
    val curState = state
    state = when (curState) {
      WaffleState.Empty -> throw Exception("Cannot take from $this: nothing inside!")
      is WaffleState.Cooking -> throw Exception("Cannot take from $this: waffle is cooking!")
      is WaffleState.Cooked -> WaffleState.Empty.also { retVal = Waffle }
      WaffleState.Burnt -> WaffleState.Empty.also { retVal = BurntWaffle }
    }
    return retVal
  }

  override fun receiveItem(player: Player, item: Item) {
    if (item is Dish) {
      item.receiveItem(player, takeFrom(player), null)
      player.heldItem = item
      return
    }
    super.receiveItem(player, item)
  }

  override fun use(player: Player) {
    if (state == WaffleState.Empty) {
      state = WaffleState.Cooking(cookTime)
      return
    }
    super.use(player)
  }
}
