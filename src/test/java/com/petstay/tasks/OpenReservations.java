package com.petstay.tasks;

import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class OpenReservations {
    private OpenReservations() {
    }

    public static Task page() {
        return Task.where("{0} consulta sus reservas",
                Open.url(OpenPetStay.url("/mascotas.html")),
                Click.on(PetsPage.RESERVATIONS_LINK));
    }
}
