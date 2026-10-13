package com.petstay.tasks;

import com.petstay.ui.BookingPage;
import com.petstay.ui.LoginPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

public class ConfirmStay {
    private ConfirmStay() {
    }

    public static Performable booking() {
        return Task.where("{0} confirma la reserva",
                actor -> {
                    actor.attemptsTo(
                            Click.on(BookingPage.CONFIRM_BUTTON),
                            WaitForLoading.toFinish());
                });
    }
}
