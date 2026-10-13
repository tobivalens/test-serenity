package com.petstay.tasks;

import com.petstay.ui.LoginPage;
import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class RegisterPet {
    private RegisterPet() {
    }

    public static Performable named(String name, String species, String weight, boolean vaccinated) {
        return Task.where("{0} registra una mascota",
                actor -> {
                    actor.attemptsTo(
                            Enter.theValue(name).into(PetsPage.PET_NAME),
                            Click.on("Perro".equals(species) ? PetsPage.DOG_OPTION : PetsPage.CAT_OPTION),
                            Enter.theValue(weight).into(PetsPage.PET_WEIGHT),
                            Enter.theValue("3").into(PetsPage.PET_AGE));
                    if (PetsPage.VACCINES.resolveFor(actor).isSelected() != vaccinated) {
                        actor.attemptsTo(Click.on(PetsPage.VACCINES));
                    }
                    actor.attemptsTo(
                            Click.on(PetsPage.REGISTER_BUTTON),
                            WaitForLoading.toFinish());
                    actor.remember("petName", name);
                });
    }

    public static Performable dogOfSize(String name, String size) {
        String weight = switch (size.toLowerCase()) {
            case "pequeño" -> "5";
            case "mediano" -> "15";
            case "grande" -> "30";
            default -> throw new IllegalArgumentException("Tamaño de perro desconocido: " + size);
        };
        return named(name, "Perro", weight, true);
    }

    public static Performable cat(String name) {
        return named(name, "Gato", "4", true);
    }
}
