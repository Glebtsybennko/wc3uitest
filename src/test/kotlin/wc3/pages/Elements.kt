package wc3.pages

import com.codeborne.selenide.Selenide.*
import org.openqa.selenium.By

class Elements {
    val button1 = `$`(By.xpath("//*[@id=\"accept-choices\"]"))
    val button = `$`(By.xpath("/html/body/div[2]/div/div[1]/div[1]/button"))
    val tableElements = `$$`(By.xpath("//*[@id=\"divResultSQL\"]/div/table/tbody/tr"))
    val table = `$`(By.xpath("//*[@id=\"divResultSQL\"]/div/table"))
    val counterForTableCustomers = `$`(By.xpath("//*[@id=\"yourDB\"]/table/tbody/tr[2]/td[2]"))
}