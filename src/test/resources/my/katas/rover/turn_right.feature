Feature: turn right
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario: rover is turning right
    Given heading is "north"
    When turning right
    Then heading should be "east"