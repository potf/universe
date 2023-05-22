*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${browser}  chrome
${url}  https://ta-java.devandqa.site/

*** Test Cases ***
Disabled Login Test
    Open Browser And Click Sign In
    input text      id:Email  1totomm@gmail.com
    input text      id:Password
    Click Log In
    Wait Until Page Contains    input field
    Close Browser

Failed Login Test
    Failed Login    totomomo@gmail.com  1234qwerty
    Failed Login    otomomo@gmail.com  5678qwerty
    Failed Login    prototomomo@gmail.com  9012qwerty
    Failed Login    botomomo@gmail.com  3456qwerty

Successful Login Test
    Successful Login    test@example.com  GNM3frcJk8AWJui@

*** Keywords ***
open browser and click Sign in
    Open Browser    ${url}    ${browser}
    click link      xpath=//a[contains(text(),'Sign in')]

Click Log In
    click element   xpath=//button[contains(.,'Sign in')]

Failed Login
    Open Browser And Click Sign In
    [Arguments]    ${mail}  ${password}
    input text      id:Email  ${mail}
    input text      id:Password  ${password}
    Click Log In
    Wait Until Page Contains    Invalid username/password, Try again!
    Close Browser

Successful Login
    Open Browser And Click Sign In
    [Arguments]    ${mail}  ${password}
    input text      id:Email  ${mail}
    input text      id:Password  ${password}
    Click Log In
    Page Should Contain Button  xpath=//a[contains(text(),'Logout')]
    Close Browser