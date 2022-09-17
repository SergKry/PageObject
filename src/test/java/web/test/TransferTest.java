package web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.data.DataHelper;
import web.page.DashBoardPage;
import web.page.LoginPage;
import web.page.MoneyTransferPage;
import web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;

public class TransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void shouldTransferFirstToSecondCard() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(new DataHelper.AuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(new DataHelper.VerificationInfo());
        int firstBalanceCard1 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard1());
        int firstBalanceCard2 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard2());
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard1();
        DashBoardPage dashBoardPage2 = moneyTransferPage.moneyTransfer("500", new DataHelper.CardInfo().getNumber2());
        int secondBalanceCard1 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard1());
        int secondBalanceCard2 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard2());
        Assertions.assertEquals(firstBalanceCard1 + 500, secondBalanceCard1);
        Assertions.assertEquals(firstBalanceCard2 - 500, secondBalanceCard2);
    }

    @Test
    void shouldTransferFirstToSecondCardNotValedMoreSum() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(new DataHelper.AuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(new DataHelper.VerificationInfo());
        int firstBalanceCard1 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard1());
        int firstBalanceCard2 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard2());
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard1();
        DashBoardPage dashBoardPage2 = moneyTransferPage.moneyTransfer("20000", new DataHelper.CardInfo().getNumber2());
        int secondBalanceCard1 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard1());
        int secondBalanceCard2 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard2());
        Assertions.assertEquals(firstBalanceCard1 + 20000, secondBalanceCard1);
        Assertions.assertEquals(firstBalanceCard2 - 20000, secondBalanceCard2);
    }
}

