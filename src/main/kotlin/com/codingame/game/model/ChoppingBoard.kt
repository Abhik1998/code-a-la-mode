package com.codingame.game.model

import com.codingame.game.Player

object Strawberries: EasilyDescribedItem(Constants.ITEM.STRAWBERRIES.name)
object ChoppedStrawberries: EdibleItem(Constants.FOOD.CHOPPED_STRAWBERRIES.name)

class ChoppingBoard: Equipment() {
  override val describeChar = 'C'
  override val tooltipString = "Chopping board"

  override fun receiveItem(player: Player, item: Item) {
    if (item === Strawberries) {
      player.heldItem = ChoppedStrawberries
      return
    }

    if (item === Dough) {
      player.heldItem = Shell()
      return
    }

    super.receiveItem(player, item)
  }
}
