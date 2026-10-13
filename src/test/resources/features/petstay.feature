# language: es
@frontend @petstay
Característica: Hospedaje de mascotas en PetStay
  Como dueño de una mascota
  Quiero registrar mis mascotas, reservar estadías y gestionar mis reservas
  Para que mis mascotas estén bien cuidadas cuando no pueda atenderlas

  # ---------------- Acceso ----------------

  @smoke @Issue-1 @REQ-ACC-01
  Escenario: Ingreso con credenciales válidas
    Dado que Laura abre la aplicación PetStay
    Cuando inicia sesión con el usuario "dueno" y la contraseña "huellitas123"
    Entonces ve la pantalla principal del hotel

  @regression @Issue-2 @REQ-ACC-02
  Escenario: Ingreso rechazado con contraseña incorrecta
    Dado que Laura abre la aplicación PetStay
    Cuando inicia sesión con el usuario "dueno" y la contraseña "clave-incorrecta"
    Entonces ve el mensaje "Usuario o contraseña incorrectos"
    Y no accede a la pantalla principal del hotel

  # ---------------- Registro de mascotas ----------------

  @regression @Issue-3 @REQ-MAS-03
  Esquema del escenario: Asignación automática del tamaño según el peso
    Dado que Laura inició sesión en PetStay
    Cuando registra un perro con un peso de <peso> kg y vacunas al día
    Entonces la lista de mascotas muestra su tamaño como "<tamaño>"

    Ejemplos:
      | peso | tamaño  |
      | 10   | Pequeño |
      | 10.5 | Mediano |
      | 24.9 | Mediano |
      | 25   | Grande  |

  @regression @Issue-4 @REQ-MAS-02
  Escenario: Registro rechazado para una mascota sin vacunas al día
    Dado que Laura inició sesión en PetStay
    Cuando intenta registrar una mascota sin vacunas al día
    Entonces ve el mensaje "No podemos hospedar mascotas sin vacunas al día"
    Y la mascota no aparece en la lista de mascotas

  @regression @Issue-5 @REQ-MAS-04
  Escenario: Registro rechazado para un nombre repetido sin distinguir mayúsculas
    Dado que Laura inició sesión en PetStay
    Y ya tiene registrada una mascota llamada "Firulais"
    Cuando intenta registrar otra mascota llamada "FIRULAIS"
    Entonces ve el mensaje "Ya existe una mascota registrada con ese nombre"
    Y la lista conserva una sola mascota con ese nombre

  # ---------------- Reserva de estadía ----------------

  @regression @Issue-6 @REQ-RES-05 @REQ-RES-10
  Escenario: Reserva de una estadía corta sin servicios adicionales
    Dado que Laura tiene registrado un perro mediano
    Cuando reserva una estadía de 3 noches que inicia dentro de 10 días sin servicios adicionales
    Entonces el resumen muestra 3 noches y un total de $165.000
    Y al confirmar recibe un código con formato PS-XXXX
    Y la reserva queda en estado "Confirmada"

  @regression @Issue-7 @REQ-RES-07
  Escenario: El descuento por más de 7 noches aplica solo sobre el alojamiento
    Dado que Laura tiene registrado un perro grande
    Cuando reserva una estadía de 8 noches con el servicio de baño y peluquería
    Entonces el resumen muestra un alojamiento de $560.000
    Y un descuento de $84.000
    Y servicios por $25.000
    Y un total de $501.000

  @regression @Issue-8 @REQ-RES-07
  Escenario: Una estadía de exactamente 7 noches no recibe descuento
    Dado que Laura tiene registrado un perro pequeño
    Cuando reserva una estadía de 7 noches sin servicios adicionales
    Entonces el resumen muestra un total de $280.000
    Y no se aplica ningún descuento

  @regression @Issue-9 @REQ-RES-06
  Escenario: El paseo diario no está disponible para gatos
    Dado que Laura tiene registrado un gato
    Cuando prepara una reserva para su mascota
    Entonces el servicio de paseo diario no está disponible
    Y ve la nota "El paseo solo está disponible para perros."

  @regression @Issue-10 @REQ-RES-04
  Escenario: Reserva rechazada por superar el máximo de 30 noches
    Dado que Laura tiene registrado un perro mediano
    Cuando intenta reservar una estadía de 31 noches
    Entonces ve el mensaje "La estadía máxima es de 30 noches"
    Y no se genera ningún resumen ni código de reserva

  @regression @Issue-11 @REQ-RES-09
  Escenario: Reserva rechazada por cruzarse con otra reserva activa de la misma mascota
    Dado que Laura tiene registrado un perro mediano
    Y tiene una reserva confirmada para esa mascota del día 10 al día 15 desde hoy
    Cuando intenta reservar a la misma mascota del día 12 al día 18 desde hoy
    Entonces ve el mensaje "Esta mascota ya tiene una reserva activa en esas fechas"
    Y no se genera ningún resumen ni código de reserva

  @regression @Issue-14 @REQ-RES-06
  Escenario: El paseo diario se cobra por cada noche de la estadía
    Dado que Laura tiene registrado un perro pequeño
    Cuando reserva una estadía de 3 noches con el servicio de paseo diario
    Entonces el resumen muestra un alojamiento de $120.000
    Y servicios por $30.000
    Y un total de $150.000

  # ---------------- Mis reservas y cancelación ----------------

  @regression @Issue-12 @REQ-CAN-03
  Esquema del escenario: Reembolso según los días que faltan para la entrada
    Dado que Laura tiene una reserva confirmada de $80.000 que inicia dentro de <dias> días
    Cuando cancela la reserva y confirma la cancelación
    Entonces la reserva queda en estado "Cancelada"
    Y el reembolso mostrado es de <reembolso>

    Ejemplos:
      | dias | reembolso |
      | 3    | $80.000   |
      | 2    | $40.000   |
      | 1    | $40.000   |
      | 0    | $0        |

  @regression @Issue-13 @REQ-CAN-04
  Escenario: Una reserva ya cancelada no se puede cancelar de nuevo
    Dado que Laura tiene una reserva que ya fue cancelada
    Cuando revisa sus reservas
    Entonces no puede volver a cancelar esa reserva
