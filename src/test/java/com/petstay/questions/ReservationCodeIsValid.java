package com.petstay.questions;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ReservationCodeIsValid {
    private ReservationCodeIsValid() {
    }

    public static Question<Boolean> value() {
        return Question.about("el código tiene el formato PS-XXXX").answeredBy(
                (Actor actor) -> Text.of(BookingPage.BOOKING_CODE).answeredBy(actor).matches("PS-[A-Z0-9]{4}"));
    }
}
