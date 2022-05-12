Feature: jugador juego 21
  Esta Feature verifica las funcionalidades del jugador en el juego 21

  Scenario: verificar que al jugador le repartan dos cartas
    Given un jugador
    When el juego inicia
    Then el jugador tiene dos cartas

  Scenario Outline: verificar que un jugador puede recibir cartas siempre y cuando no se superen los 21 puntos
    Given un jugador
    When el juego inicia
    When el jugador tiene un <puntaje>
    And el jugador solicita una carta

    Examples:
      | puntaje | estado |
      | 8       | true   |
      | 23      | false  |
      | 20      | true   |
      | 26      | false  |
      | 15      | true   |
      | 22      | false  |

  Scenario Outline: Determinar el puntaje de los jugadores y definir el ganador
    Given un jugador
    When se conoce el <puntaje_jugador> y el <puntaje_casa>
    Then se conoce el <ganador>

    Examples:
      | puntaje_jugador | puntaje_casa | ganador           |
      | 16              | 21           | "Gano la casa"    |
      | 20              | 19           | "Gano el jugador" |
      | 19              | 19           | "Gano la casa"    |
      | 22              | 24           | "Nadie gano"      |