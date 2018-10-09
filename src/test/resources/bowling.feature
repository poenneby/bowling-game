Feature: Compute a bowling game score for one player
  As a bowling game player
  In order to know if I'm the best
  I want to compute my score

  Background:
    Given a new bowling game

  Scenario: Score is zero at the beginning of the game
    Then the score should be 0

  Scenario Outline: Score is the sum of pins down
    When I roll and <pins down> fall
    Then the score should be <expected score>
    Examples:
      | pins down                               | expected score |
      | 3                                       | 3              |
      | 3,6                                     | 9              |
      | 3,6,4                                   | 13             |
      | 0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 | 10             |

  Scenario Outline: A spare gets a bonus equal to the following roll hits
    When I roll and <pins down> fall
    Then the score should be <expected score>
    Examples:
      | pins down | expected score |
      | 3,7,4     | 18             |
      | 3,6,4,6,2 | 23             |

  Scenario Outline: A strike gets a bonus equal to the following 2 roll hits
    When I roll and <pins down> fall
    Then the score should be <expected score>
    Examples:
      | pins down                           | expected score |
      | 10,4,2                              | 22             |
      | 3,6,10,6,2                          | 35             |
      | 3,6,10,10,2,5                       | 55             |
      | 10,10,10,10,10,10,10,10,10,10,10,10 | 300            |
