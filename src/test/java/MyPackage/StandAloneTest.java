package MyPackage;

import AloneReUsability.AloneReUsables;
import PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends AloneReUsables {
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        //String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));

    }

    @DataProvider
    public Object[] getData() throws IOException {
        //HashMap<String,String> map = new HashMap<String,String>();
        //map.put("email", "dee@gmail.com");
        //map.put("password", "Myfirstframe1");
        //map.put("productName", "ZARA COAT 3");
        List<HashMap<String,String>> data = getJsonToMap();
        return new Object[] {data};
    }


}


