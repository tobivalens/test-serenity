package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target USERNAME = Target.the("campo de usuario").locatedBy("[name='usuario']");
    public static final Target PASSWORD = Target.the("campo de contraseña").locatedBy("#pass-01");
    public static final Target LOGIN_BUTTON = Target.the("botón ingresar").locatedBy("[data-testid='btn-ingresar']");
    public static final Target LOGIN_ERROR = Target.the("mensaje de error de acceso").locatedBy("#error-login");
    public static final Target PET_FORM = Target.the("formulario de mascota").locatedBy("#form-mascota");
    public static final Target LOADING_LAYER = Target.the("capa de carga").locatedBy("#capa-cargando");
}
