package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PetsPage {
    public static final Target PET_NAME = Target.the("nombre de la mascota").locatedBy("#nombreMascota");
    public static final Target DOG_OPTION = Target.the("opción perro").locatedBy("[name='especie'][value='Perro']");
    public static final Target CAT_OPTION = Target.the("opción gato").locatedBy("[name='especie'][value='Gato']");
    public static final Target PET_WEIGHT = Target.the("peso de la mascota").locatedBy("#pw-4k");
    public static final Target PET_AGE = Target.the("edad de la mascota").locatedBy("#e-2");
    public static final Target VACCINES = Target.the("vacunas al día").locatedBy("[name='vacunas']");
    public static final Target REGISTER_BUTTON = Target.the("botón registrar mascota").locatedBy("#form-mascota button[type='submit']");
    public static final Target PET_MESSAGE = Target.the("mensaje del registro de mascota").locatedBy("[data-testid='mensaje-mascotas']");
    public static final Target PET_NAME_ERROR = Target.the("error del nombre de la mascota").locatedBy("span[data-campo='nombre']");
    public static final Target PET_NAMES = Target.the("nombres en la lista de mascotas").locatedBy("#tabla-mascotas tbody td.nombre");
    public static final Target PET_ROWS = Target.the("filas de mascotas").locatedBy("#tabla-mascotas tbody tr");
    public static final Target RESERVATIONS_LINK = Target.the("enlace a mis reservas").locatedBy("#enlace-reservas");

    public static Target petRow(String name) {
        return Target.the("fila de " + name)
                .locatedBy("//tr[td[contains(@class,'nombre') and normalize-space()='" + name + "']]");
    }

    public static Target petSize(String name) {
        return Target.the("tamaño de " + name)
                .locatedBy("//tr[td[contains(@class,'nombre') and normalize-space()='" + name + "']]//*[contains(concat(' ', normalize-space(@class), ' '), ' etiqueta-tamano ')]");
    }

    public static Target reservePetLink(String name) {
        return Target.the("enlace para reservar a " + name)
                .locatedBy("//tr[td[contains(@class,'nombre') and normalize-space()='" + name + "']]//a[starts-with(@href,'reservar.html')]");
    }

    public static Target petNameCount(String name) {
        String normalized = name.toLowerCase();
        return Target.the("nombres coincidentes con " + name)
                .locatedBy("//table[@id='tabla-mascotas']//tbody/tr[td[contains(@class,'nombre') and translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + normalized + "']]");
    }
}
