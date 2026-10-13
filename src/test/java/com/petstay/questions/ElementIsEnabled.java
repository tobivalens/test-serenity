package com.petstay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElementIsEnabled {
    private ElementIsEnabled() {
    }

    public static Question<Boolean> forTarget(Target target) {
        return Question.about("si el control está habilitado")
                .answeredBy((Actor actor) -> target.resolveFor(actor).isEnabled());
    }
}
