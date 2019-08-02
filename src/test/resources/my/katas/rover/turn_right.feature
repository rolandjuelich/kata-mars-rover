Feature: Turning
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: rover turns to the right
    Given heading is "<given>"
    When turning right
    Then heading should be "<expected>"

    Examples: 
      | given | expected |
      | north | east     |
      | east  | south    |
      | south | west     |
      | west  | north    |
