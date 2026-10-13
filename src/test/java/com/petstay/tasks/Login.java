package com.petstay.tasks;

import com.petstay.ui.LoginPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login {
    private Login() {
    }

    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} inicia sesión",
                actor -> {
                    actor.attemptsTo(
                            Enter.theValue(username).into(LoginPage.USERNAME),
                            Enter.theValue(password).into(LoginPage.PASSWORD),
                            Click.on(LoginPage.LOGIN_BUTTON),
                            WaitForLoading.toFinish());
                });
    }
}
