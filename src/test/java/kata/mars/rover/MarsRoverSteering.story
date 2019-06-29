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
