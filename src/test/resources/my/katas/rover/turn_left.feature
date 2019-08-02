Feature: Turning
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: rover turns to the left
    Given heading is "<given>"
    When turning left
    Then heading should be "<expected>"

    Examples: 
      | given | expected |
      | north | west     |
      | west  | south    |
      | south | east     |
      | east  | north    |
