Feature: Moving
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Scenario Outline: rover moves forward heading north
    Given location is at 0, <given>
    And heading is "north"
    When moving forward <times> times
    Then location should be 0, <expected>
    And heading should be "north"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |        1 |
      |     0 |     2 |        2 |
      |     0 |     1 |        1 |
      |    99 |     1 |        0 |
      |    99 |   100 |       99 |
      |    99 |   201 |        0 |
      |    99 |   302 |        1 |

  Scenario Outline: rover moves forward heading east
    Given location is at <given>, 0
    And heading is "east"
    When moving forward <times> times
    Then location should be <expected>, 0
    And heading should be "east"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |        1 |
      |     0 |     2 |        2 |
      |     0 |     1 |        1 |
      |    99 |     1 |        0 |
      |    99 |   100 |       99 |
      |    99 |   201 |        0 |
      |    99 |   302 |        1 |

  Scenario Outline: rover moves forward heading south
    Given location is at 0, <given>
    And heading is "south"
    When moving forward <times> times
    Then location should be 0, <expected>
    And heading should be "south"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |       99 |
      |     0 |   100 |        0 |
      |     0 |   201 |       99 |
      |     0 |   302 |       98 |
      |    99 |    99 |        0 |
      |    99 |   100 |       99 |
      |    99 |   201 |       98 |
      |    99 |   302 |       97 |

  Scenario Outline: rover moves forward heading west
    Given location is at <given>, 0
    And heading is "west"
    When moving forward <times> times
    Then location should be <expected>, 0
    And heading should be "west"

    Examples: 
      | given | times | expected |
      |     0 |     0 |        0 |
      |     0 |     1 |       99 |
      |     0 |   100 |        0 |
      |     0 |   201 |       99 |
      |     0 |   302 |       98 |
      |    99 |    99 |        0 |
      |    99 |   100 |       99 |
      |    99 |   201 |       98 |
      |    99 |   302 |       97 |
