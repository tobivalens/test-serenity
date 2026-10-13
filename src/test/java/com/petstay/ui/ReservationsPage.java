package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ReservationsPage {
    public static final Target TABLE = Target.the("tabla de reservas").locatedBy("[data-testid='tabla-reservas']");
    public static final Target CANCEL_CONFIRM = Target.the("confirmar cancelación").locatedBy("#confirmar-cancelacion");
    public static final Target MESSAGE = Target.the("mensaje de reservas").locatedBy("#mensaje-reservas");

    public static Target reservationRow(String code) {
        return Target.the("reserva " + code)
                .locatedBy("//tr[td[contains(@class,'codigo') and normalize-space()='" + code + "']]");
    }

    public static Target reservationStatus(String code) {
        return Target.the("estado de " + code).locatedBy(
                "//tr[td[contains(@class,'codigo') and normalize-space()='" + code + "']]//*[contains(concat(' ', normalize-space(@class), ' '), ' estado ')]");
    }

    public static Target refundAmount(String code) {
        return Target.the("reembolso de " + code).locatedBy(
                "//tr[td[contains(@class,'codigo') and normalize-space()='" + code + "']]/td[contains(@class,'reembolso')]");
    }

    public static Target cancelButton(String code) {
        return Target.the("cancelar reserva " + code).locatedBy(".btn-cancelar[data-codigo='" + code + "']");
    }
}
