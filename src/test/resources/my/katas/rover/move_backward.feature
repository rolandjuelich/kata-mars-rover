Feature: rover moves backward
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: heading north
    Given location is at 0, 100
    And heading is "north"
    When moving backward times <times>
    Then location should be 0, <new_y>
    And heading should be "north"

    Examples: 
      | times | new_y |
      |     1 |    99 |
      |    42 |    58 |
      |   100 |     0 |
      |   142 |    42 |

  Scenario Outline: heading east
    Given location is at 100, 0
    And heading is "east"
    When moving backward times <times>
    Then location should be <new_x>, 0
    And heading should be "east"

    Examples: 
      | times | new_x |
      |     1 |    99 |
      |    42 |    58 |
      |   100 |     0 |
      |   142 |    42 |

  Scenario Outline: heading south
    Given location is at 0, 0
    And heading is "south"
    When moving backward times <times>
    Then location should be 0, <new_y>
    And heading should be "south"

    Examples: 
      | times | new_y |
      |     1 |     1 |
      |    42 |    42 |
      |   100 |   100 |
      |   142 |    42 |

  Scenario Outline: heading west
    Given location is at 0, 0
    And heading is "west"
    When moving backward times <times>
    Then location should be <new_x>, 0
    And heading should be "west"

    Examples: 
      | times | new_x |
      |     1 |     1 |
      |    42 |    42 |
      |   100 |   100 |
      |   142 |    42 |
