@001TC
@all
Feature: Financial Services - Functionality

  Background: Common steps
    Given I landed on watchsted website
    And I can see page heading "THE LATEST OFSTED INSPECTIONS"
    When I click Search link
    And I can see page title "Watchsted - Keyword Search"
    And I should be taken to search page title "Watchsted - Keyword Search"
    And I should see buttons in search page as follows:
      | Primary Schools |
      | Overall (EIF)   |
      | Any             |

  Scenario Outline:
    When I search with keyword "<Search keyword>"
    And I should see search result for the "<Search keyword>"
    Then I can see the search result summary report message as follows:
      | Primary School           |
      | Overall (EIF)            |
      | Any                      |
      | Inspection(s)            |
      | liverpool                |
      | Main Findings            |
      | See the 10 most relevant |
    Then I can see the <Relevant Search result count> most relevant search result
    And I close browser
    Examples:
      | Search keyword | Relevant Search result count |
      | liverpool      | 10                           |

  Scenario Outline:
    When I search with invalid city name "<Search keyword>"
    Then I should not see search result "<Search result>"
    And I close browser
    Examples:
      | Search keyword | Search result                                                                          |
      | liver-pool     | None of the recent inspections contain the keyword you were looking for. Please try another. |
      | LIVER POOL     | None of the recent inspections contain the keyword you were looking for. Please try another. |
