Feature: Turning
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: rover turns to the left
    Given rover is heading "<given>"
    When rover turns left
    Then rover should be heading "<expected>"

    Examples: 
      | given | expected |
      | north | west     |
      | west  | south    |
      | south | east     |
      | east  | north    |
