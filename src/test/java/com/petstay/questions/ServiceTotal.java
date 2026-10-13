package com.petstay.questions;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ServiceTotal {
    private ServiceTotal() {
    }

    public static Question<String> value() {
        return Question.about("el total de los servicios").answeredBy((Actor actor) -> {
            int bath = amount(Text.of(BookingPage.BATH_AMOUNT).answeredBy(actor));
            int walk = amount(Text.of(BookingPage.WALK_AMOUNT).answeredBy(actor));
            return String.format("$%,d", bath + walk).replace(",", ".");
        });
    }

    private static int amount(String displayedAmount) {
        String digits = displayedAmount.replaceAll("\\D", "");
        return digits.isEmpty() ? 0 : Integer.parseInt(digits);
    }
}
