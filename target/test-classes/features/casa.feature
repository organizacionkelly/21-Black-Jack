Feature: casa juego 21
  Esta Feature verifica las funcionalidades asociadas a la casa en el juego 21

  Scenario: verificar que como casa me repartan dos cartas
    Given la casa
    When inicia el juego
    Then la casa tiene dos cartas

  Scenario Outline: verificar que la casa pueda recibir cartas siempre y cuando no se superen los 21 puntos y el jugador se haya plantado
    Given la casa
    When el jugador se planta
    And se valida el <puntaje> de la casa
    Then se reparte una carta : <estado>

    Examples:
      | puntaje | estado |
      | 15      | true   |
      | 18      | true   |
      | 22      | false  |
      | 23      | false  |
      | 12      | true   |

  Scenario Outline: realizar el conteo de mis cartas
    Given la casa
    When se suman las cartas: <mano>
    Then el conteo es: <puntaje>

    Examples:
      | mano      | puntaje |
      | "4,5,As"  | 20      |
      | "J,Q,As"  | 21      |
      | "2,8,8"   | 18      |
      | "J,K"     | 20      |
      | "J,Q,6"   | 26      |
      | "2,5,7,3" | 17      |
      | "As,As,K" | 12      |


  Scenario Outline: Conocer el resultado final para saber el ganador
    Given la casa
    When se sabe el <puntaje_jugador> y el <puntaje_casa>
    Then se sabe el <ganador>

    Examples:
      | puntaje_jugador | puntaje_casa    | ganador           |
      | 16              | 21              | "Gano la casa"    |
      | 20              | 19              | "Gano el jugador" |
      | 19              | 19              | "Gano la casa"    |
      | 22              | 24              | "Nadie gano"      |