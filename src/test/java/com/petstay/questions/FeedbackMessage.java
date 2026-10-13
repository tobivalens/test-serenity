package com.petstay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class FeedbackMessage {
    private FeedbackMessage() {
    }

    public static Question<Boolean> isVisible(String expectedMessage) {
        return Question.about("el mensaje esperado está visible").answeredBy((Actor actor) -> {
            Object result = ((JavascriptExecutor) BrowseTheWeb.as(actor).getDriver())
                    .executeScript("return document.body.innerText.includes(arguments[0]);", expectedMessage);
            return Boolean.TRUE.equals(result);
        });
    }
}
