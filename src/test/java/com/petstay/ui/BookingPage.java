package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class BookingPage {
    public static final Target PET_SELECT = Target.the("selector de mascota").locatedBy("select[name='mascota']");
    public static final Target CHECK_IN = Target.the("fecha de entrada").locatedBy("#fecha-entrada");
    public static final Target CHECK_OUT = Target.the("fecha de salida").locatedBy("[data-testid='fecha-salida']");
    public static final Target BATH_SERVICE = Target.the("baño y peluquería").locatedBy("#srv-bano");
    public static final Target WALK_SERVICE = Target.the("paseo diario").locatedBy("[name='paseo']");
    public static final Target WALK_NOTE = Target.the("nota sobre el paseo").locatedBy(".nota-paseo");
    public static final Target CALCULATE_BUTTON = Target.the("botón calcular total").locatedBy("#btn-calcular");
    public static final Target SUMMARY = Target.the("resumen de la reserva").locatedBy(".panel-resumen");
    public static final Target NIGHTS = Target.the("noches del resumen").locatedBy("[data-testid='res-noches']");
    public static final Target ACCOMMODATION = Target.the("alojamiento del resumen").locatedBy("#res-alojamiento");
    public static final Target DISCOUNT = Target.the("descuento del resumen").locatedBy("[data-testid='res-descuento']");
    public static final Target BATH_AMOUNT = Target.the("importe del baño").locatedBy(".res-bano");
    public static final Target WALK_AMOUNT = Target.the("importe del paseo").locatedBy("#res-paseo");
    public static final Target TOTAL = Target.the("total de la reserva").locatedBy("[data-testid='res-total']");
    public static final Target DISCOUNT_ROW = Target.the("fila de descuento").locatedBy(".fila-descuento");
    public static final Target BATH_ROW = Target.the("fila de baño").locatedBy(".fila-bano");
    public static final Target WALK_ROW = Target.the("fila de paseo").locatedBy(".fila-paseo");
    public static final Target CONFIRM_BUTTON = Target.the("botón confirmar reserva").locatedBy(".panel-resumen .btn-primario");
    public static final Target BOOKING_MESSAGE = Target.the("mensaje de confirmación").locatedBy("[data-testid='mensaje-reserva']");
    public static final Target BOOKING_CODE = Target.the("código de reserva").locatedBy("[data-testid='codigo-reserva']");
    public static final Target CHECK_IN_ERROR = Target.the("error de fecha de entrada").locatedBy("span[data-campo='entrada']");
    public static final Target CHECK_OUT_ERROR = Target.the("error de fecha de salida").locatedBy("span[data-campo='salida']");
    public static final Target PET_ERROR = Target.the("error de mascota").locatedBy("span[data-campo='mascota']");
}
