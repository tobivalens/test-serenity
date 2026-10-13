package com.petstay.stepdefinitions;

import com.petstay.tasks.CancelReservation;
import com.petstay.tasks.ConfirmStay;
import com.petstay.tasks.Login;
import com.petstay.tasks.OpenPetStay;
import com.petstay.tasks.OpenReservations;
import com.petstay.tasks.RegisterPet;
import com.petstay.tasks.RequestStayQuote;
import com.petstay.tasks.VerifyPetStay;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class PetStayStepDefinitions {

    @Dado("que {actor} abre la aplicación PetStay")
    public void opensPetStay(Actor actor) {
        actor.attemptsTo(OpenPetStay.page());
    }

    @Cuando("inicia sesión con el usuario {string} y la contraseña {string}")
    public void logsIn(String username, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(Login.withCredentials(username, password));
    }

    @Entonces("ve la pantalla principal del hotel")
    public void seesPetDashboard() {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.dashboardIsVisible());
    }

    @Entonces("ve el mensaje {string}")
    public void seesMessage(String expectedMessage) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.messageIs(expectedMessage));
    }

    @Entonces("no accede a la pantalla principal del hotel")
    public void doesNotSeePetDashboard() {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.dashboardIsNotVisible());
    }

    @Dado("que {actor} inició sesión en PetStay")
    public void logsInToPetStay(Actor actor) {
        createSession(actor);
    }

    @Cuando("registra un perro con un peso de {weight} kg y vacunas al día")
    public void registersVaccinatedDog(String weight) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.remember("petName", "Rocky");
        actor.attemptsTo(RegisterPet.named("Rocky", "Perro", weight, true));
    }

    @Entonces("la lista de mascotas muestra su tamaño como {string}")
    public void seesPetSize(String expectedSize) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerifyPetStay.petSizeIs(actor.recall("petName"), expectedSize));
    }

    @Cuando("intenta registrar una mascota sin vacunas al día")
    public void triesToRegisterPetWithoutVaccines() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.remember("petName", "Luna");
        actor.attemptsTo(RegisterPet.named("Luna", "Perro", "15", false));
    }

    @Entonces("la mascota no aparece en la lista de mascotas")
    public void petIsNotListed() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerifyPetStay.petIsNotListed(actor.recall("petName")));
    }

    @Dado("ya tiene registrada una mascota llamada {string}")
    public void alreadyHasPet(String name) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.remember("petName", name);
        actor.attemptsTo(RegisterPet.named(name, "Perro", "15", true));
    }

    @Cuando("intenta registrar otra mascota llamada {string}")
    public void triesToRegisterDuplicatePet(String name) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.remember("petName", name);
        actor.attemptsTo(RegisterPet.named(name, "Perro", "15", true));
    }

    @Entonces("la lista conserva una sola mascota con ese nombre")
    public void onlyOnePetWithNameRemains() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerifyPetStay.onlyOnePetWithName(actor.recall("petName")));
    }

    @Dado("que {actor} tiene registrado un perro mediano")
    public void hasRegisteredMediumDog(Actor actor) {
        registerDog(actor, "mediano");
    }

    @Dado("que {actor} tiene registrado un perro pequeño")
    public void hasRegisteredSmallDog(Actor actor) {
        registerDog(actor, "pequeño");
    }

    @Dado("que {actor} tiene registrado un perro grande")
    public void hasRegisteredLargeDog(Actor actor) {
        registerDog(actor, "grande");
    }

    @Dado("que {actor} tiene registrado un gato")
    public void hasRegisteredCat(Actor actor) {
        createSession(actor);
        actor.remember("petName", "Misi");
        actor.attemptsTo(RegisterPet.cat("Misi"));
    }

    @Cuando("reserva una estadía de {int} noches que inicia dentro de {int} días sin servicios adicionales")
    public void requestsStayWithoutServices(int nights, int startInDays) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.forPet(actor.recall("petName"), startInDays, nights, false, false));
    }

    @Cuando("reserva una estadía de {int} noches con el servicio de baño y peluquería")
    public void requestsStayWithBath(int nights) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.forPet(actor.recall("petName"), 10, nights, true, false));
    }

    @Cuando("reserva una estadía de {int} noches sin servicios adicionales")
    public void requestsStayWithoutExtras(int nights) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.forPet(actor.recall("petName"), 10, nights, false, false));
    }

    @Entonces("el resumen muestra {int} noches y un total de {money}")
    public void summaryShowsNightsAndTotal(int nights, String expectedTotal) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.quoteHasNightsAndTotal(nights, expectedTotal));
    }

    @Entonces("al confirmar recibe un código con formato PS-XXXX")
    public void confirmsAndGetsCode() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ConfirmStay.booking());
        actor.remember("reservationCode", VerifyPetStay.currentReservationCode(actor));
        actor.attemptsTo(VerifyPetStay.bookingCodeIsValid());
    }

    @Entonces("la reserva queda en estado {string}")
    public void reservationHasStatus(String expectedStatus) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(OpenReservations.page(),
                VerifyPetStay.reservationStatusIs(actor.recall("reservationCode"), expectedStatus));
    }

    @Entonces("el resumen muestra un alojamiento de {money}")
    public void summaryShowsAccommodation(String amount) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.accommodationIs(amount));
    }

    @Entonces("un descuento de {money}")
    public void summaryShowsDiscount(String amount) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.discountIs(amount));
    }

    @Entonces("servicios por {money}")
    public void summaryShowsServiceTotal(String amount) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.servicesTotalIs(amount));
    }

    @Entonces("un total de {money}")
    public void summaryShowsTotal(String amount) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.totalIs(amount));
    }

    @Entonces("el resumen muestra un total de {money}")
    public void summaryShowsOnlyTotal(String amount) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.totalIs(amount));
    }

    @Entonces("no se aplica ningún descuento")
    public void noDiscountIsApplied() {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.discountIsNotApplied());
    }

    @Cuando("prepara una reserva para su mascota")
    public void preparesBookingForPet() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.prepareForm(actor.recall("petName"), 10, 2, false, false));
    }

    @Entonces("el servicio de paseo diario no está disponible")
    public void walkingServiceIsUnavailable() {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.walkingIsUnavailable());
    }

    @Entonces("ve la nota {string}")
    public void seesWalkNote(String expectedNote) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.walkNoteIs(expectedNote));
    }

    @Cuando("intenta reservar una estadía de {int} noches")
    public void triesToBookTooLongStay(int nights) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.forPet(actor.recall("petName"), 10, nights, false, false));
    }

    @Entonces("no se genera ningún resumen ni código de reserva")
    public void noSummaryOrBookingCodeIsGenerated() {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyPetStay.noBookingWasGenerated());
    }

    @Dado("tiene una reserva confirmada para esa mascota del día {int} al día {int} desde hoy")
    public void hasConfirmedBookingBetween(int start, int end) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.betweenDays(actor.recall("petName"), start, end, false, false),
                ConfirmStay.booking());
    }

    @Cuando("intenta reservar a la misma mascota del día {int} al día {int} desde hoy")
    public void triesToOverlapBooking(int start, int end) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.betweenDays(actor.recall("petName"), start, end, false, false));
    }

    @Dado("que {actor} tiene una reserva confirmada de $80.000 que inicia dentro de {int} días")
    public void hasConfirmedEightyThousandBooking(Actor actor, int startInDays) {
        createSession(actor);
        actor.attemptsTo(RegisterPet.dogOfSize("Rocky", "pequeño"),
                RequestStayQuote.forPet("Rocky", startInDays, 2, false, false),
                ConfirmStay.booking());
        actor.remember("reservationCode", VerifyPetStay.currentReservationCode(actor));
        actor.attemptsTo(OpenReservations.page());
    }

    @Cuando("cancela la reserva y confirma la cancelación")
    public void cancelsAndConfirmsReservation() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CancelReservation.withCode(actor.recall("reservationCode")));
    }

    @Entonces("el reembolso mostrado es de {money}")
    public void refundIs(String expectedRefund) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerifyPetStay.refundIs(actor.recall("reservationCode"), expectedRefund));
    }

    @Dado("que {actor} tiene una reserva que ya fue cancelada")
    public void hasAlreadyCancelledBooking(Actor actor) {
        createSession(actor);
        actor.attemptsTo(RegisterPet.dogOfSize("Rocky", "pequeño"),
                RequestStayQuote.forPet("Rocky", 10, 2, false, false),
                ConfirmStay.booking());
        actor.remember("reservationCode", VerifyPetStay.currentReservationCode(actor));
        actor.attemptsTo(OpenReservations.page(), CancelReservation.withCode(actor.recall("reservationCode")));
    }

    @Cuando("revisa sus reservas")
    public void checksReservations() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenReservations.page());
    }

    @Entonces("no puede volver a cancelar esa reserva")
    public void cannotCancelAgain() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerifyPetStay.cannotCancelAgain(actor.recall("reservationCode")));
    }

    @Cuando("reserva una estadía de {int} noches con el servicio de paseo diario")
    public void requestsStayWithWalking(int nights) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RequestStayQuote.forPet(actor.recall("petName"), 10, nights, false, true));
    }

    private void createSession(Actor actor) {
        actor.attemptsTo(OpenPetStay.page(), Login.withCredentials("dueno", "huellitas123"));
    }

    private void registerDog(Actor actor, String size) {
        createSession(actor);
        actor.remember("petName", "Rocky");
        actor.attemptsTo(RegisterPet.dogOfSize("Rocky", size));
    }
}
