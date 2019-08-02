Feature: rover moves forward
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: heading north
    Given location is at 0, <given>
    And heading is "north"
    When moving forward times <times>
    Then location should be 0, <expected>
    And heading should be "north"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |        1 |
      |   100 |     1 |        0 |
      |    42 |   100 |       41 |
      |    42 |   201 |       42 |
      |    42 |   302 |       43 |

  Scenario Outline: heading east
    Given location is at <given>, 0
    And heading is "east"
    When moving forward times <times>
    Then location should be <expected>, 0
    And heading should be "east"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |        1 |
      |    42 |     1 |       43 |
      |   100 |     1 |        0 |
      |    42 |   100 |       41 |
      |    42 |   200 |       41 |
      |    42 |   300 |       41 |

  Scenario Outline: heading south
    Given location is at 0, <given>
    And heading is "south"
    When moving forward times <times>
    Then location should be 0, <expected>
    And heading should be "south"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |      100 |
      |     0 |   100 |        0 |
      |     0 |   201 |        1 |
      |     0 |   302 |        2 |
      |   100 |    99 |        1 |
      |   100 |   100 |        0 |
      |   100 |   201 |       99 |
      |   100 |   302 |       98 |

  Scenario Outline: heading west
    Given location is at <given>, 0
    And heading is "west"
    When moving forward times <times>
    Then location should be <expected>, 0
    And heading should be "west"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |      100 |
      |     0 |   100 |        0 |
      |     0 |   201 |        1 |
      |     0 |   302 |        2 |
      |   100 |    99 |        1 |
      |   100 |   100 |        0 |
      |   100 |   201 |       99 |
      |   100 |   302 |       98 |
