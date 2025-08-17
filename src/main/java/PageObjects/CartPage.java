package PageObjects;

import ReusableComponents.ReUsables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends ReUsables {

    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath= "//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(css= ".totalRow button")
    WebElement checkoutEle;

    public Boolean verifyProductDisplay(String productName)
    {
        Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckOut()
    {
       checkoutEle.click();
        CheckOutPage checkOutPage =new CheckOutPage(driver);
        return checkOutPage;
    }

}
