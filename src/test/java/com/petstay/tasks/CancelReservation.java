package com.petstay.tasks;

import com.petstay.ui.LoginPage;
import com.petstay.ui.ReservationsPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class CancelReservation {
    private CancelReservation() {
    }

    public static Task withCode(String code) {
        return Task.where("{0} cancela la reserva",
                Click.on(ReservationsPage.cancelButton(code)),
                Click.on(ReservationsPage.CANCEL_CONFIRM),
                WaitForLoading.toFinish());
    }
}
