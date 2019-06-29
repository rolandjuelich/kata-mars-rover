Steering a mars rover

Narrative:
In order to explore mars safely
As a scientist
I want to use a mars rover 
					 
Scenario: the mars rover rejects invalid initialization parameters
Given rover to be initialized with heading <heading> at <x>, <y>
When the rover is initialized 
Then it rejects with message <message>

Examples:
| heading | x | y | message |
| A | 0| 0 | given heading('A') is not one of N,S,E,W |
| B | 3| 4 | given heading('B') is not one of N,S,E,W |
| C | 9| 8 | given heading('C') is not one of N,S,E,W |

| N | -1| 0 | X (-1) must be between 0 and 100 |
| S | -101 | 101 | X (-101) must be between 0 and 100 |
| E | 101| 0 | X (101) must be between 0 and 100 |
| W | 342| 3121 | X (342) must be between 0 and 100 |

					 
Scenario: the mars rover moves backward
Given a rover heading <givenHeading> at <givenX>, <givenY>
When rover moves backward
Then rover is heading <expectedHeading> at <expectedX>, <expectedY>

Examples:
| givenHeading | givenX | givenY | expectedHeading | expectedX | expectedY |

| S | 0| 0 | S | 0| 1 |
| S | 9| 1 | S | 9| 2 |
| S | 5| 2 | S | 5| 3 |
| S | 0| 100 | S | 0| 0 |

| N | 0| 1 | N | 0| 0 |
| N | 5| 2 | N | 5| 1 |
| N | 8| 3 | N | 8| 2 |
| N | 0| 0 | N | 0| 100 |

| W | 0| 0 | W | 1| 0 |
| W | 1| 1 | W | 2| 1 |
| W | 2| 5 | W | 3| 5 |
| W | 100| 0 | W | 0| 0 |

| E | 1| 0 | E | 0| 0 |
| E | 2| 5 | E | 1| 5 |
| E | 3| 8 | E | 2| 8 |
| E | 0| 0 | E | 100| 0 |

Scenario: the mars rover moves forward
Given a rover heading <givenHeading> at <givenX>, <givenY>
When rover moves forward
Then rover is heading <expectedHeading> at <expectedX>, <expectedY>

Examples:
| givenHeading | givenX | givenY | expectedHeading | expectedX | expectedY |

| N | 0| 0 | N | 0| 1 |
| N | 9| 1 | N | 9| 2 |
| N | 5| 2 | N | 5| 3 |
| N | 0| 100 | N | 0| 0 |

| S | 0| 1 | S | 0| 0 |
| S | 5| 2 | S | 5| 1 |
| S | 8| 3 | S | 8| 2 |
| S | 0| 0 | S | 0| 100 |


| E | 0| 0 | E | 1| 0 |
| E | 1| 1 | E | 2| 1 |
| E | 2| 5 | E | 3| 5 |
| E | 100| 0 | E | 0| 0 |

| W | 1| 0 | W | 0| 0 |
| W | 2| 5 | W | 1| 5 |
| W | 3| 8 | W | 2| 8 |
| W | 0| 0 | W | 100| 0 |


