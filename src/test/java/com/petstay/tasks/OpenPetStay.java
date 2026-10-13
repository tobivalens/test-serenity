package com.petstay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.core.Serenity;

public class OpenPetStay {
    private OpenPetStay() {
    }

    public static Task page() {
        return Task.where("{0} abre PetStay", Open.url(url("/")));
    }

    public static String url(String path) {
        String baseUrl = Serenity.environmentVariables().getProperty("webdriver.base.url");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("No está configurada webdriver.base.url");
        }
        return baseUrl.replaceAll("/+$", "") + (path.startsWith("/") ? path : "/" + path);
    }
}
