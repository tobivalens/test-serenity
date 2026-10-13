package com.petstay.tasks;

import com.petstay.ui.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

public class WaitForLoading {
    private WaitForLoading() {
    }

    public static Performable toFinish() {
        return Task.where("{0} espera a que termine la carga",
                actor -> {
                    var driver = BrowseTheWeb.as(actor).getDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ZERO);
                    try {
                        actor.attemptsTo(WaitUntil.the(LoginPage.LOADING_LAYER,
                                WebElementStateMatchers.isNotVisible()).forNoMoreThan(60).seconds());
                    } finally {
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
                    }
                });
    }
}
