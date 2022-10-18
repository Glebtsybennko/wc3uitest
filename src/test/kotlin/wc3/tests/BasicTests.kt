package wc3.tests


import com.codeborne.selenide.Configuration
import org.openqa.selenium.chrome.ChromeDriver
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import org.junit.jupiter.api.*
import wc3.pages.Steps

class BasicTests {
    var steps = Steps()
    @BeforeEach
    fun openSite() {
        val hostname = System.getProperty("HUB_HOST")
        Configuration.remote = "http://host.docker.internal:4444/wd/hub";
        Configuration.browser = "chrome";
        open("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    }

    @Test
    @DisplayName("1 проверка")
    fun firstsCheck() {
        steps.skipCookie()
        steps.runForm()
        steps.checkTableItemContent("Via Ludovico il Moro 22", "Giovanni Rovelli")
    }

    @Test
    @DisplayName("2 проверка")
    fun secondCheck() {
        steps.skipCookie()
        steps.executeSql("SELECT * FROM Customers WHERE city =\"London\"")
        steps.runForm()
        steps.checkCountOfTable(6)
    }

    @Test
    @DisplayName("3 проверка")
    fun thirdCheck() {
        steps.skipCookie()
        steps.executeSql("INSERT INTO Customers (CustomerID,CustomerName,ContactName,Address,City,PostalCode,Country) VALUES (\"333\",\"Test1\",\"Test2\",\"Test3\",\"Test4\",\"Test5\",\"Test6\")")
        steps.runForm()
        steps.executeSql("SELECT * FROM Customers WHERE CustomerID =\"333\"")
        Thread.sleep(1000)
        steps.runForm()
        steps.checkStringInTable("333", "Test1", "Test2", "Test3", "Test4", "Test5", "Test6")
    }

    @Test
    @DisplayName("4 проверка")
    fun fourthCheck() {
        steps.skipCookie()
        steps.executeSql("UPDATE Customers SET CustomerName=\"Test1\",ContactName=\"Test1\",Address=\"Test1\",City=\"Test1\",PostalCode=\"Test1\",Country=\"Test1\" where CustomerID=\"1\"")
        steps.runForm()
        steps.executeSql("SELECT * FROM Customers WHERE CustomerID =\"1\"")
        Thread.sleep(1000)
        steps.runForm()
        steps.checkStringInTable("1", "Test1", "Test1", "Test1", "Test1", "Test1", "Test1")
    }

    @Test
    @DisplayName("5 проверка")
    fun fifthCheck() {
        steps.skipCookie()
        steps.runForm()
        steps.checkCounterForTable("91")
        steps.executeSql("INSERT INTO Customers (CustomerID,CustomerName,ContactName,Address,City,PostalCode,Country) VALUES (\"333\",\"Test1\",\"Test2\",\"Test3\",\"Test4\",\"Test5\",\"Test6\")")
        steps.runForm()
        Thread.sleep(100)
        steps.checkCounterForTable("92")

    }

    @AfterEach
    fun closeSite() {
        closeWebDriver()
    }
}