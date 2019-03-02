package com.codingame.game.view

import com.codingame.game.Player
import com.codingame.game.Referee
import com.codingame.game.ScoreBoard

class ScoresView(matchPlayers: List<Player>) {

  var currentRoundNumber = 0    /* 0, 1, 2 */

  val playerScoreViews = matchPlayers.mapIndexed { i, player ->
    player to PlayerScoreView(player).apply {
      group.x = 10
      group.y = 10 + (i * (1080 - 20 - 200))
    }
  }.toMap()

  fun update(scores: ScoreBoard) {
    scores.forEach { player, entry ->
      playerScoreViews[player]!!.update(entry)
      playerScoreViews[player]!!.messageText.text = (player.message)
    }
  }

  inner class PlayerScoreView(player: Player) {
    val viewWidth = 410
    val viewHeight = 200

    private val playerAvatar = graphicEntityModule.createSprite().apply {
      image = player.avatarToken
      x = (0.5 * viewWidth / 4).toInt()
      y = viewHeight / 4
      anchorX = 0.5
      anchorY = 0.5
      baseWidth = viewWidth / 4
      baseHeight = viewWidth / 4
      zIndex = 350
    }

    private val playerNameText = graphicEntityModule.createText(player.nicknameToken).apply {
      x = viewWidth / 2
      y = viewHeight * 3 / 4
      anchorX = 0.5
      anchorY = 0.5
      fontSize = 50
      fillColor = 0
      zIndex = 350

    }

    private val scoreTexts = // List(3) { i ->
      graphicEntityModule.createText("--").apply {
        fillColor = 0xffffff
        fontSize = 45
        x = viewWidth / 2
        y = viewHeight * 1 / 4
        anchorX = 0.5
        anchorY = 0.5
        zIndex = 350
      }
    //}

    val messageText =
      graphicEntityModule.createText("").apply {
        fillColor = 0xffffff
        fontSize = 35
        x = ((1 + 1.5) * viewWidth / 4).toInt()
        y = viewHeight * 2 / 4
        anchorX = 0.5
        anchorY = 0.5
        zIndex = 350
      }


    val backgroundBox = graphicEntityModule.createRectangle().apply {
      fillColor = player.colorToken
      width = viewWidth
      height = viewHeight
      zIndex = 200
    }!!

    val group = graphicEntityModule.createGroup().apply {
      add(scoreTexts)
      add(backgroundBox)
      add(playerAvatar)
      add(playerNameText)
      add(messageText)
    }

    fun update(entry: Referee.ScoreEntry) {
      if (currentRoundNumber >= 3) return
      entry.roundScores[currentRoundNumber]?.let { scoreTexts.text = it.toString() }
    }

  }
}