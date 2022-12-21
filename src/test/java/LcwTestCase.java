import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LcwTestCase {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void Setup() {
        driver = new ChromeDriver();
        driver.get("https://www.lcwaikiki.com/tr-TR/TR");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void SuccessfulTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cookieseal-banner-body']/button[text()='Tüm Çerezlere İzin Ver']")));
        WebElement cookiesAccepted = driver.findElement(By.xpath("//div[@class='cookieseal-banner-body']/button[text()='Tüm Çerezlere İzin Ver']"));
        cookiesAccepted.click();

        WebElement searchBox = driver.findElement(By.id("search-form__input-field__search-input"));
        Assert.assertTrue(searchBox.isDisplayed());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-form__input-field__search-input")));
        WebElement searchBox1 = driver.findElement(By.id("search-form__input-field__search-input"));
        searchBox1.sendKeys("pantalon");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class,'search-form')]"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='paginator__button']")));
        WebElement moreProductButton = driver.findElement(By.xpath("//button[@class='paginator__button']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moreProductButton);
        moreProductButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'product-card')][101]")));
        WebElement selectProduct = driver.findElement(By.xpath("//div[contains(@class,'product-card')][101]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectProduct);
        selectProduct.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'col-md-12')]//div[@class='option-size']/a[1]")));
        WebElement sizeSelected = driver.findElement(By.xpath("//div[contains(@class,'col-md-12')]//div[@class='option-size']/a[1]"));
        sizeSelected.click();

        String basketDiscount = driver.findElement(By.xpath("//div[contains(@class,'col-xs-12 price')]//div[@class='basket-discount' and text()='118,99 TL']")).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pd_add_to_cart']")));
        WebElement addToBasket = driver.findElement(By.xpath("//a[@id='pd_add_to_cart']"));
        addToBasket.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='dropdown-label' and text()='Sepetim']")));
        WebElement goToBasket = driver.findElement(By.xpath("//span[@class='dropdown-label' and text()='Sepetim']"));
        goToBasket.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'col-md-4')]/span[2]")));
        String basketDiscount1 = driver.findElement(By.xpath("//div[contains(@class,'col-md-4')]/span[2]")).getText();
        Assert.assertEquals(basketDiscount, basketDiscount1);

        WebElement upPlus = driver.findElement(By.xpath("//a[@class='oq-up plus']"));
        upPlus.click();

        String plusText = driver.findElement(By.xpath("//input[contains(@class,'item-quantity')]")).getAttribute("value");
        Assert.assertEquals("2" , plusText);

        WebElement deleteProduct = driver.findElement(By.xpath("//a[@class='cart-square-link']"));
        deleteProduct.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='inverted-modal-button sc-delete']")));
        WebElement deleteProductButton = driver.findElement(By.xpath("//a[@class='inverted-modal-button sc-delete']"));
        deleteProductButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='cart-empty-title']")));
        String basketText = driver.findElement(By.xpath("//p[@class='cart-empty-title']")).getText();
        Assert.assertEquals(basketText, "Sepetinizde ürün bulunmamaktadır.");
    }

    @After
    public void CloseDriver() {
        driver.close();
    }
}
