package com.petstay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElementCount {
    private ElementCount() {
    }

    public static Question<Integer> of(Target target) {
        return Question.about("cantidad de elementos").answeredBy((Actor actor) -> target.resolveAllFor(actor).size());
    }
}
