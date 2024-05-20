Feature: Splash Screen to Home Screen Load Time

  Scenario: Calculate Load Time from Splash to Home Screen
    Given the application is launched
    When I navigate from the splash screen to the home screen
    Then the load time should be calculated and logged