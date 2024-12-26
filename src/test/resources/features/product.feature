Feature: Product Management

  Scenario: Get all products
    Given the following products exist
      | name        | price | quantity |
      | Laptop      | 1000  | 10       |
      | Smartphone  | 500   | 20       |
    When I request all products
    Then I should receive a list of products
