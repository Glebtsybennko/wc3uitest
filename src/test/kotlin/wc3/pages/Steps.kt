package wc3.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import org.openqa.selenium.*
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class Steps {
    var elements = Elements()
    fun skipCookie() {
        val skipButton = elements.button1
        skipButton.click()
    }

    fun runForm() {
        val sqlButton = elements.button
        sqlButton.click()
    }

    fun checkTableItemContent(expectedAddress: String, expectedName: String) {
        var table = elements.table
        val tableElements = elements.tableElements
        table.shouldBe(visible.because("Таблица не отобразилась"))
        assertTrue(tableElements.size != 0, "В таблице нет записей")

        for (i in 1 until tableElements.size) {
            val contactName = tableElements[i].`$$`(By.tagName("td"))[2].text()
            if (contactName == expectedName) {
                val address = tableElements[i].`$$`(By.tagName("td"))[3].text()
                assertEquals(expectedAddress, address, "Искомый адрес не совпадает")
                return
            }
        }
        assertTrue(false, "Не найдено искомое имя")
    }

    fun executeSql(value: String) {
        Selenide.executeJavaScript<String>(
            "window.editor.getDoc().setValue(\'$value\')"
        )
    }

    fun checkCountOfTable(expectedCount: Int) {
        val table = elements.table
        table.shouldBe(exist.because("Таблица не найдена"))
        elements.tableElements.should(
            CollectionCondition
                .size(expectedCount + 1)
                .because("Количество записей в таблице не совпадает")
        )
    }

    fun checkStringInTable(
        CustomerID: String,
        CustomerName: String,
        ContactName: String,
        Address: String,
        City: String,
        PostalCode: String,
        Country: String
    ) {
        val table = elements.table
        table.shouldBe(exist.because("Таблица не найдена"))
        val tableElements = elements.tableElements
        assertEquals(1, tableElements.size - 1, "Параметр не уникальный")
        for (index in 0..6) {
            when (index) {
                0 -> assertEquals(
                    CustomerID,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "CustomerId не совпадает"
                )
                1 -> assertEquals(
                    CustomerName,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "CustomerName не совпадает"
                )
                2 -> assertEquals(
                    ContactName,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "ContactName не совпадает"
                )
                3 -> assertEquals(
                    Address,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "Address не совпадает"
                )
                4 -> assertEquals(City, tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "City не совпадает"
                )
                5 -> assertEquals(
                    PostalCode,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "PostalCode не совпадает"
                )
                6 -> assertEquals(
                    Country,
                    tableElements[1].`$$`(By.tagName("td"))[index].text(),
                    "Country не совпадает"
                )
            }
        }

    }
    fun checkCounterForTable(expectedValue:String) {
        val counter = elements.counterForTableCustomers
        counter.should(exist,Duration.ofSeconds(5))
        assertEquals(expectedValue,counter.text,"Значения не совпадают")
    }
}