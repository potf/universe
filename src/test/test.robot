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
    Click Sing In And Close Browser

Failed Login Test
    Open Browser And Click Sign In
    input text      id:Email  totomomo@gmail.com
    input text      id:Password  1234qwerty
    Click Sing In And Close Browser

Successful Login Test
    Open Browser And Click Sign In
    input text      id:Email  test@example.com
    input text      id:Password  GNM3frcJk8AWJui@
    Click Sing In And Close Browser

*** Keywords ***
open browser and click Sign in
    open browser    ${url}    ${browser}
    click link      xpath=//a[contains(text(),'Sign in')]

Click Sing in and Close Browser
    click element   xpath=//button[contains(.,'Sign in')]
    close browser