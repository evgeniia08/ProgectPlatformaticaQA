Feature: Entity fields

  Scenario: Invalid int value
    Given Go to Fields
    Then Push add button
    When Input invalid int "str" and save
#    Then We are on error page

  Scenario Outline: Add new record
    Given Go to Fields
    Then Push add button
    When Input "<title>", "<comment>", "<int>", "<decimal>", "<date>" and save
    Then Result record is "<title>", "<comment>", "<int>", "<decimal>", "<date>"

  Examples:
    | title     | comment     | int | decimal | date       |
    | title-123 | comment-123 | 25  | 12.34   | 01/12/2020 |
    | title-098 | comment-098 | 35  | 12.01   | 07/11/2019 |