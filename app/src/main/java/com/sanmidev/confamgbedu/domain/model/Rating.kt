package com.sanmidev.confamgbedu.domain.model

import java.lang.IllegalArgumentException

enum class Rating(val value: Float) {
    ZERO(0.0f),
    ONE(1.0f),
    TWO(2.0f),
    THREE(3.0f),
    FOUR(4.0f),
    FIVE(5.0f),
    SIX(6.0f),
    SEVEN(7.0f),
    EIGHT(8.0f),
    NINE(9.0f),
    TEN(10.0f);

    companion object {
        fun processUserRating(rating: Float): Rating {
            return when (rating) {
                0.0f -> ZERO
                1.0f -> ONE
                2.0f -> TWO
                3.0f -> THREE
                4.0f -> FOUR
                5.0f -> FIVE
                6.0f -> SIX
                7.0f -> SEVEN
                8.0f -> EIGHT
                9.0f -> NINE
                10.0f -> TEN
                else -> throw IllegalArgumentException("Rating can not be less than zero or more than ten.")
            }
        }
    }
}
