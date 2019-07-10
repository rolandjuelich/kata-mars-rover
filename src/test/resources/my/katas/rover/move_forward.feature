Feature: rover moves forward 
  
	In order to explore mars
	As a scientist
	I want to use a rover to check the terrain 

  Scenario: one step towards north
    Given location is at 0, 0
    And heading is NORTH
    When executing command forward
    Then location should be 0, 1
    And heading should be NORTH