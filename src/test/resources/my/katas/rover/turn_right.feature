Feature: Turning
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: rover turns to the right
    Given rover is heading "<given>"
    When rover turns right
    Then rover should be heading "<expected>"

    Examples: 
      | given | expected |
      | north | east     |
      | east  | south    |
      | south | west     |
      | west  | north    |
