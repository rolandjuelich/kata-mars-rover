Steering a mars rover

Narrative:
In order to explore mars safely
As a scientist
I want to use a mars rover 
					 
Scenario: the mars rover moves forward
Given rover location is 0,0
And rover heading is north
When rover moves forward
Then rover is expected to be at 0,1
And rover is expected to be heading north
					 
