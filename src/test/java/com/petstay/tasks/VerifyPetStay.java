package com.petstay.tasks;

import com.petstay.questions.ElementCount;
import com.petstay.questions.ElementIsEnabled;
import com.petstay.questions.FeedbackMessage;
import com.petstay.questions.PetNameExists;
import com.petstay.questions.ReservationCodeIsValid;
import com.petstay.questions.ReservationCodeValue;
import com.petstay.questions.ServiceTotal;
import com.petstay.ui.BookingPage;
import com.petstay.ui.LoginPage;
import com.petstay.ui.PetsPage;
import com.petstay.ui.ReservationsPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;

public class VerifyPetStay {
    private VerifyPetStay() {
    }

    public static Task dashboardIsVisible() {
        return Task.where("{0} verifica la pantalla principal",
                Ensure.that(Visibility.of(LoginPage.PET_FORM)).isTrue());
    }

    public static Task dashboardIsNotVisible() {
        return Task.where("{0} verifica que no accedió",
                Ensure.that(Visibility.of(LoginPage.PET_FORM)).isFalse());
    }

    public static Task messageIs(String expected) {
        return Task.where("{0} verifica el mensaje",
                Ensure.that(FeedbackMessage.isVisible(expected)).isTrue());
    }

    public static Task petSizeIs(String name, String size) {
        return Task.where("{0} verifica el tamaño de la mascota",
                Ensure.that(Text.of(PetsPage.petSize(name))).isEqualTo(size));
    }

    public static Task petIsNotListed(String name) {
        return Task.where("{0} verifica que la mascota no está registrada",
                Ensure.that(PetNameExists.named(name)).isFalse());
    }

    public static Task onlyOnePetWithName(String name) {
        return Task.where("{0} verifica que el nombre aparece una sola vez",
                Ensure.that(ElementCount.of(PetsPage.petNameCount(name))).isEqualTo(1));
    }

    public static Task quoteHasNightsAndTotal(int nights, String total) {
        return Task.where("{0} verifica las noches y el total",
                Ensure.that(Text.of(BookingPage.NIGHTS)).isEqualTo(Integer.toString(nights)),
                Ensure.that(Text.of(BookingPage.TOTAL)).isEqualTo(total));
    }

    public static Task bookingCodeIsValid() {
        return Task.where("{0} verifica el código de reserva",
                Ensure.that(ReservationCodeIsValid.value()).isTrue());
    }

    public static String currentReservationCode(net.serenitybdd.screenplay.Actor actor) {
        return ReservationCodeValue.value().answeredBy(actor);
    }

    public static Task reservationStatusIs(String code, String status) {
        return Task.where("{0} verifica el estado de la reserva",
                Ensure.that(Text.of(ReservationsPage.reservationStatus(code))).isEqualTo(status));
    }

    public static Task accommodationIs(String amount) {
        return Task.where("{0} verifica el alojamiento",
                Ensure.that(Text.of(BookingPage.ACCOMMODATION)).isEqualTo(amount));
    }

    public static Task discountIs(String amount) {
        return Task.where("{0} verifica el descuento",
                Ensure.that(Text.of(BookingPage.DISCOUNT)).isEqualTo("-" + amount));
    }

    public static Task servicesTotalIs(String amount) {
        return Task.where("{0} verifica el importe de los servicios",
                Ensure.that(ServiceTotal.value()).isEqualTo(amount));
    }

    public static Task totalIs(String amount) {
        return Task.where("{0} verifica el total",
                Ensure.that(Text.of(BookingPage.TOTAL)).isEqualTo(amount));
    }

    public static Task discountIsNotApplied() {
        return Task.where("{0} verifica que no hay descuento",
                Ensure.that(Visibility.of(BookingPage.DISCOUNT_ROW)).isFalse());
    }

    public static Task walkingIsUnavailable() {
        return Task.where("{0} verifica que el paseo no está disponible",
                Ensure.that(ElementIsEnabled.forTarget(BookingPage.WALK_SERVICE)).isFalse());
    }

    public static Task walkNoteIs(String note) {
        return Task.where("{0} verifica la nota del paseo",
                Ensure.that(Text.of(BookingPage.WALK_NOTE)).isEqualTo(note));
    }

    public static Task noBookingWasGenerated() {
        return Task.where("{0} verifica que no se generó una reserva",
                Ensure.that(Visibility.of(BookingPage.SUMMARY)).isFalse(),
                Ensure.that(Visibility.of(BookingPage.BOOKING_CODE)).isFalse());
    }

    public static Task refundIs(String code, String refund) {
        return Task.where("{0} verifica el reembolso",
                Ensure.that(Text.of(ReservationsPage.refundAmount(code))).isEqualTo(refund));
    }

    public static Task cannotCancelAgain(String code) {
        return Task.where("{0} verifica que la reserva ya no puede cancelarse",
                Ensure.that(ElementCount.of(ReservationsPage.cancelButton(code))).isEqualTo(0));
    }
}
