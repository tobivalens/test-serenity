package com.petstay.tasks;

import com.petstay.ui.BookingPage;
import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestStayQuote {
    private RequestStayQuote() {
    }

    public static Performable forPet(String name, int startInDays, int nights, boolean bath, boolean walk) {
        return Task.where("{0} solicita el cálculo de una estadía",
                actor -> {
                    actor.attemptsTo(
                            Open.url(OpenPetStay.url("/mascotas.html")),
                            Click.on(PetsPage.reservePetLink(name)),
                            Enter.theValue(date(startInDays)).into(BookingPage.CHECK_IN),
                            Enter.theValue(date(startInDays + nights)).into(BookingPage.CHECK_OUT));
                    configureServices(actor, bath, walk);
                    actor.attemptsTo(Click.on(BookingPage.CALCULATE_BUTTON));
                });
    }

    public static Performable betweenDays(String name, int startInDays, int endInDays, boolean bath, boolean walk) {
        return forPet(name, startInDays, endInDays - startInDays, bath, walk);
    }

    public static Performable prepareForm(String name, int startInDays, int nights, boolean bath, boolean walk) {
        return Task.where("{0} prepara una estadía",
                actor -> {
                    actor.attemptsTo(
                            Open.url(OpenPetStay.url("/mascotas.html")),
                            Click.on(PetsPage.reservePetLink(name)),
                            Enter.theValue(date(startInDays)).into(BookingPage.CHECK_IN),
                            Enter.theValue(date(startInDays + nights)).into(BookingPage.CHECK_OUT));
                    configureServices(actor, bath, walk);
                });
    }

    private static void configureServices(net.serenitybdd.screenplay.Actor actor, boolean bath, boolean walk) {
        if (BookingPage.BATH_SERVICE.resolveFor(actor).isSelected() != bath) {
            actor.attemptsTo(Click.on(BookingPage.BATH_SERVICE));
        }
        if (BookingPage.WALK_SERVICE.resolveFor(actor).isSelected() != walk) {
            actor.attemptsTo(Click.on(BookingPage.WALK_SERVICE));
        }
    }

    private static String date(int daysFromToday) {
        return LocalDate.now().plusDays(daysFromToday).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
