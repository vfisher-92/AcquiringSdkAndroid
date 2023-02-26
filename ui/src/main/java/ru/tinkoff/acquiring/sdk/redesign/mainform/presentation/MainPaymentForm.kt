package ru.tinkoff.acquiring.sdk.redesign.mainform.presentation

import ru.tinkoff.acquiring.sdk.models.Card
import ru.tinkoff.acquiring.sdk.redesign.common.emailinput.EmailValidator
import ru.tinkoff.acquiring.sdk.redesign.dialog.PaymentStatusSheetState
import ru.tinkoff.acquiring.sdk.redesign.payment.model.CardChosenModel
import ru.tinkoff.acquiring.sdk.responses.Paymethod
import ru.tinkoff.acquiring.sdk.responses.TerminalInfo
import ru.tinkoff.acquiring.sdk.ui.customview.editcard.validators.CardValidator
import ru.tinkoff.acquiring.sdk.utils.Money

internal object MainPaymentForm {

    data class State(
        val ui: Ui,
        val data: Data,
        val noInternet : Boolean = false
    )

    data class Ui(
        val primary: Primary,
        val secondaries: Set<Secondary>
    )

    data class Data(
        val info: TerminalInfo,
        val cards: List<Card>,
        val chosen: Card?
    )

    sealed class Primary(val paymethod: Paymethod) {

        object Tpay : Primary(Paymethod.TinkoffPay)

        object Spb : Primary(Paymethod.SBP)

        data class Card(val selectedCard: CardChosenModel?) : Primary(Paymethod.Cards)
    }

    sealed class Secondary(val paymethod: Paymethod, val order: Int) {
        object Tpay : Secondary(Paymethod.TinkoffPay, 1)

        object Spb : Secondary(Paymethod.SBP, 3)

        data class Cards(val count: Int) : Secondary(Paymethod.Cards, 2)

        object Yandex : Secondary(Paymethod.YandexPay, 4)
    }
}