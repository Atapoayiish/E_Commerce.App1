package MyPackage;

import AloneReUsability.AloneReUsables;
import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ConfirmationPage;
import PageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends AloneReUsables {
    @Test
    public void LoginErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";
        landingPage.loginApplication("dee@gmail.com", "Myfirstframe");
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Incorrect email password.");

    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("dee@gmail.com", "Myfirstframe1");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertTrue(match);

    }

}


