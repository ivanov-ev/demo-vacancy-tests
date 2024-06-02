# Sample Test Automation Project in Java for the [HFLABS.RU](https://hflabs.ru/) Website

---

HFLabs provides customer data quality and data integration solutions and services in Russia. Founded in 2005.

<a href="hhttps://hflabs.ru/"><img src="./images/logos/hflabs_logo.png"/></a>

---

## Contents:

- <a href="#tools">Tools and Technologies</a>
- <a href="#scenarios">Test Scenarios</a>
- <a href="#jenkins">CI/CD with Jenkins</a>
- <a href="#cli">Launch from the CLI</a>
- <a href="#allure">Allure Reports</a>
- <a href="#allure-testops">Integration with Allure TestOps</a>
- <a href="#jira">Integration with JIRA</a>
- <a href="#telegram">Telegram Bot Notifications</a>
- <a href="#video">Test Execution Example</a>

---

<a id="tools"></a>
## Tools and Technologies:

| Java                                                                                                       | IntelliJ  <br>  Idea | GitHub | JUnit 5 | Gradle | Selenide | Selenoid | Allure <br> Report | Jenkins | Jira                                                                                                                              | Telegram | Allure <br> TestOps                                                                                                     |
|------------------------------------------------------------------------------------------------------------|----------------------|--------|---------|--------|----------|----------|--------------------|---------|-----------------------------------------------------------------------------------------------------------------------------------|----------|-------------------------------------------------------------------------------------------------------------------------|
| <a href="https://www.java.com/"><img src="./images/logos/java.svg" width="50" height="50"  alt="Java"/></a> | <a href="https://www.jetbrains.com/idea/"><img src="./images/logos/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="./images/logos/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="./images/logos/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="./images/logos/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="./images/logos/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="./images/logos/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework/allure2"><img src="./images/logos/Allure.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://www.jenkins.io/"><img src="./images/logos/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/ru/software/jira/"><img src="./images/logos/jira.svg" width="50" height="50"  alt="Jira"/></a> | <a href="https://telegram.org/"><img src="./images/logos/Telegram.svg" width="50" height="50"  alt="Telegram"/></a> | <a href="https://qameta.io/"><img src="./images/logos/Allure_TO.svg" width="50" height="50"  alt="Allure TestOps"/></a> |

---

<a id="scenarios"></a>
## Test Scenarios
* [x] Click the close icon in the cookies bottom bar to close the bar (Automated)
* [x] Click the request submission button to open the form, and check the form is displayed correctly and can be closed (Automated)
* [x] Change the language, and check the language is changed on the main page (Automated)
* [x] Click the 'Products' item in the top bar, and check its sub-menu's items and links (Automated)
* [x] Click the 'Company' item in the top bar, and check its sub-menu's items and links (Automated)
* [x] Check the blog page: https://blog.hflabs.ru/ (Manual)
* [x] Check the contacts page: https://hflabs.ru/contacts/ (Manual)

---

<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="./images/logos/Jenkins.svg" width="25"/></a><a name="CI/CD with Jenkins"></a>CI/CD with [Jenkins](https://jenkins.autotests.cloud/job/demo-vacancy-tests-ivanov-ev/)</a>

<img alt="Jenkins" src="./images/screenshots/Jenkins.png">

### Jenkins parameters:

- `BROWSER` (a web-browser, `chrome` by default)
- `BROWSERVERSION` (a browser version)
- `BROWSERSIZE` (a browser window size)
- `REMOTE` (a remote Selenoid server)

---

<a id="cli"></a>
## Launch from the CLI

**Local:**
```bash  
./gradle clean smoke_tests
```

**Remote launch in Jenkins:**
```bash  
clean smoke_tests -Dselenide.browser=${BROWSER} -Dselenide.browserVersion=${BROWSERVERSION} -Dselenide.browserSize=${BROWSERSIZE} -Dselenide.remote=${REMOTE}
```
---

<a id="allure"></a>
## <img alt="AllureReports" height="25" src="./images/logos/Allure.svg" width="25"/></a> <a name="Allure"></a>[Allure Reports](https://jenkins.autotests.cloud/job/demo-vacancy-tests-ivanov-ev/allure/)</a>

`Allure report` includes:
- Test steps
- Screenshot of the page
- Page source
- Browser console logs
- Video recording

<img alt="Allure" src="./images/screenshots/AllureReports.png"> 

<img alt="Allure2" src="./images/screenshots/AllureReports2.png">

---



<a id="allure-testops"></a>
## <img alt="Allure" height="25" src="./images/logos/Allure_TO.svg" width="25"/></a> Integration with <a target="_blank" href="https://allure.autotests.cloud/project/4266/dashboards">Allure TestOps</a>

Test cases and test execution history are available in `Allure TestOps`. 

The dashboard displays test run statistics:

<img alt="Dashboard" src="./images/screenshots/Dashboard.png">

Automated test cases:

<img alt="Cases" src="./images/screenshots/AutomatedTestCases.png">

Manual test cases:

<img alt="Cases" src="./images/screenshots/ManualTestCases.png">

Launches:

<img alt="Launch" src="./images/screenshots/Launches.png">

---

<a id="jira"></a>
## <img alt="Jira" height="25" src="./images/logos/Jira.svg" width="25"/></a> Integration with <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1254">Jira</a>

There is a task in `Jira` that contains references to Allure test cases and Allure launches:

<img alt="JIRA" src="./images/screenshots/JiraTask.png">

---

<a id="telegram"></a>
## <img alt="Telegram" height="25" src="./images/logos/Telegram.svg" width="25"/></a> Telegram Bot Notifications
After every launch, the Telegram bot sends a notification with an `Allure report`:

<img alt="Bot" src="./images/screenshots/TelegramBot.png"> 

---

<a id="video"></a>
## <img alt="Selenoid" height="25" src="./images/logos/Selenoid.svg" width="25"/></a> Test Execution Example

<img alt="Video" src="./images/screenshots/TestExecutionExample.gif"
