package com.petstay.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class PetStayHooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @ParameterType("[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+|él|ella")
    public Actor actor(String nombre) {
        return OnStage.theActorCalled(nombre);
    }

    @ParameterType("\\$[0-9.]+")
    public String money(String amount) {
        return amount;
    }

    @ParameterType("\\d+(?:\\.\\d+)?")
    public String weight(String value) {
        return value;
    }
}
