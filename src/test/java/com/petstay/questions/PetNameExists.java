package com.petstay.questions;

import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class PetNameExists {
    private PetNameExists() {
    }

    public static Question<Boolean> named(String name) {
        return Question.about("la lista contiene a " + name).answeredBy((Actor actor) ->
                Text.ofEach(PetsPage.PET_NAMES).answeredBy(actor).stream().anyMatch(pet -> pet.equalsIgnoreCase(name)));
    }
}
