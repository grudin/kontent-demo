# Kontent Demo
Demonstration of timeout issue with nested `DeliveryClient`s using Kentico Kontent Delivery Java SDK 3.2.0.

## Explanation of Issue
A `DeliveryClient` configured to use a `ContentLinkUrlResolver` that in itself uses the same instance of that
`DeliveryClient` causes the initial HTTP request made to Kontent to stall until the timeout is reached (60 seconds.)
Subsequent requests made by the `ContentLinkUrlResolver` will execute normally.

## How to Run
From project root directory, run `./gradlew bootRun` to start the application on port 8080.
Visiting http://localhost:8080/test should reproduce the timeout issue.
