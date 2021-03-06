Feature: Moving
  
  In order to explore mars
  As a scientist
  I want to use a rover to check the terrain

  Background: 
    Given following terrains
      | name  | minX | maxX | minY | maxY |
      | Mars  |    0 |   99 |    0 |   99 |
      | Earth |    0 |   99 |    0 |   99 |
    And target terrain is "Mars"

  Scenario Outline: rover moves forward heading north
    Given rover is heading "north" at 0, <given>
    When rover moves forward <times> times
    Then rover should be heading "north" at 0, <expected>

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
    Given rover is heading "east" at <given>, 0
    When rover moves forward <times> times
    Then rover should be heading "east" at <expected>, 0

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
    Given rover is heading "south" at 0, <given>
    When rover moves forward <times> times
    Then rover should be heading "south" at 0, <expected>

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
    Given rover is heading "west" at <given>, 0
    When rover moves forward <times> times
    Then rover should be heading "west" at <expected>, 0

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
