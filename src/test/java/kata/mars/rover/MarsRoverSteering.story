Steering a mars rover

Narrative:
In order to explore mars safely
As a scientist
I want to use a mars rover 
					 
Scenario: the mars rover moves forward
Given a rover heading <givenHeading> at <givenX>, <givenY>
When rover moves forward
Then rover is heading <expectedHeading> at <expectedX>, <expectedY>

Examples:
| givenHeading | givenX | givenY | expectedHeading | expectedX | expectedY |

| N | 0| 0 | N | 0| 1 |
| N | 9| 1 | N | 9| 2 |
| N | 5| 2 | N | 5| 3 |

| S | 0| 1 | S | 0| 0 |
| S | 5| 2 | S | 5| 1 |
| S | 8| 3 | S | 8| 2 |

| E | 0| 0 | E | 1| 0 |
| E | 1| 1 | E | 2| 1 |
| E | 2| 5 | E | 3| 5 |

| W | 1| 0 | W | 0| 0 |
| W | 2| 5 | W | 1| 5 |
| W | 3| 8 | W | 2| 8 |


