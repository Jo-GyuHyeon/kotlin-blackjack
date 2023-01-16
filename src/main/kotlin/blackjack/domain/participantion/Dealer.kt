package blackjack.domain.participantion

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.result.Losers
import blackjack.domain.result.Winners

class Dealer(name: String, cards: Cards) : Participant(name, cards, Price(0)) {
    constructor(cardDeck: CardDeck) : this(
        name = DEFAULT_NAME,
        cards = Cards(cardDeck)
    )

    fun isHittable() = cards.point() <= HIT_LIMIT_POINT

    private fun earn(price: Price) = this.price.increase(price)

    fun settleDealerPrice(winners: Winners, losers: Losers, dealer: Dealer) {
        if (dealer.isBust()) {
            return
        }

        val losersTotalPrice = losers.getTotalPrice()
        val winnersTotalPrice = winners.getTotalPrice()
        val blackJackIncomePrice = winners.blackJackIncome(dealer)

        val settleAmount = dealer.priceAmount
            .plus(losersTotalPrice.amount)
            .minus(winnersTotalPrice.amount)
            .minus(blackJackIncomePrice.amount)

        earn(Price(settleAmount))
    }

    companion object {
        private const val HIT_LIMIT_POINT = 16
        private const val DEFAULT_NAME = "딜러"
    }
}
