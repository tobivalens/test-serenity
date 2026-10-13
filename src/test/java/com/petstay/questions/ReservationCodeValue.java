package com.petstay.questions;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ReservationCodeValue {
    private ReservationCodeValue() {
    }

    public static Question<String> value() {
        return Question.about("el código generado para la reserva")
                .answeredBy((Actor actor) -> Text.of(BookingPage.BOOKING_CODE).answeredBy(actor).trim());
    }
}
