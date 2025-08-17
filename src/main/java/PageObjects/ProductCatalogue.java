package PageObjects;

import ReusableComponents.ReUsables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends ReUsables {

    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css= ".ng-animating")
    WebElement animating;

    @FindBy(css= ".mb-3")
    List<WebElement> products;


    By productsBy = By.cssSelector(".mb-3");
   By addToCart = By.xpath("//div[@class='card-body']/button[2]");
   By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = (WebElement) products.stream().filter(s->
                s.findElement(By.xpath("//div/h5/b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(animating);

    }



}
