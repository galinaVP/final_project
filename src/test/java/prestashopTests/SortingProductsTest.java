package prestashopTests;

import blocks.ProductBlock;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.AllProductsPage;
import pages.MainPage;

public class SortingProductsTest extends BaseTest {

  @Test(description = "Check that sorting products is correct test")
  public void checkThatSortingProductsIsCorrectTest() {

    MainPage mainPage = new MainPage();
    SoftAssertions softAssertions = new SoftAssertions();

    mainPage.openMainPage()
        .clickOnAllProductsButton()
        .getSortingBlock().sortByNameFromAToZ();      // Sort products as 'Name, A to Z'

    AllProductsPage allProductsPage = new AllProductsPage();
    // get products after sorting 'Name, A to Z'
    List<ProductBlock> productsAfterSortingNamesFromAToZ = allProductsPage.getProductsFromAllProductsPage();

    // Check that sorting 'Name, A to Z' is correct
    boolean isSortedNamesFromAToZ = allProductsPage.isSortedNamesFromAToZ(
        productsAfterSortingNamesFromAToZ);
    softAssertions.assertThat(isSortedNamesFromAToZ)
        .as("Sorting products as 'Name, A to Z' is incorrect")
        .isTrue();

    allProductsPage
        .getSortingBlock().sortByNameFromZToA();      // Sort products as 'Name, Z to A'
    // get names products after sorting 'Name, Z to A'
    List<ProductBlock> productsAfterSortingNamesFromZToA = allProductsPage.getProductsFromAllProductsPage();

    // Check that sorting 'Name, Z to A' is correct
    boolean isSortedNamesFromZToA = allProductsPage.isSortedNameFromZToA(
        productsAfterSortingNamesFromZToA);
    softAssertions.assertThat(isSortedNamesFromZToA)
        .as("Sorting products as 'Name, Z to A' is incorrect")
        .isTrue();

    allProductsPage
        .getSortingBlock().sortByPriceFromLowToHigh();   // Sort products as 'Price, low to high'
    // get prices products after sorting 'Price, low to high'
    List<ProductBlock> productsAfterSortingPriceFromLowToHigh = allProductsPage.getProductsFromAllProductsPage();

    // Check that sorting 'Price, low to high' is correct
    boolean isSortedPriceFromLowToHigh = allProductsPage.isSortedPriceFromLowToHigh(
        productsAfterSortingPriceFromLowToHigh);
    softAssertions.assertThat(isSortedPriceFromLowToHigh)
        .as("Sorting products as 'Price, low to high' is incorrect")
        .isTrue();

    allProductsPage
        .getSortingBlock().sortByPriceFromHighToLow();   // Sort products as 'Price, high to low'
    // get prices products after sorting 'Price, high to low'
    List<ProductBlock> productsAfterSortingPriceFromHighToLow = allProductsPage.getProductsFromAllProductsPage();

    // Check that sorting 'Price, high to low' is correct
    boolean isSortedPriceFromHighToLow = allProductsPage.isSortedPriceFromHighToLow(
        productsAfterSortingPriceFromHighToLow);
    softAssertions.assertThat(isSortedPriceFromHighToLow)
        .as("Sorting products as 'Price, high to low' is incorrect")
        .isTrue();

    softAssertions.assertAll();
  }
}