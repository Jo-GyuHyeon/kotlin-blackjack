package blackjack.domain.result

import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price

sealed class Rank(
    private val players: List<Player>
) {

    fun exist(name: String): Boolean =
        players.any { player -> player.name == name }

    fun getTotalPrice(): Price = Price(players.sumOf { it.priceAmount })

    fun getBlackJackPlayers(): List<Player> = players.filter { player -> player.isBlackJack() }
}
