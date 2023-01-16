package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price

class Winners(val players: List<Player>) : Rank(players) {

    fun blackJackIncome(dealer: Dealer): Price {
        val blackJackPlayers = getBlackJackPlayers()

        if (dealer.isBlackJack()) {
            return Price.ZERO
        }

        val incomeAmount: Double = blackJackPlayers.sumOf { player ->
            player.price.times(BLACK_JACK_TIMES)
        }

        return Price(incomeAmount.toInt())
    }

    companion object {
        private const val BLACK_JACK_TIMES = 0.5

        fun from(dealer: Dealer, players: List<Player>): Winners {
            val winners = players.filter { player -> player.isWin(dealer) }

            return Winners(winners)
        }
    }
}
