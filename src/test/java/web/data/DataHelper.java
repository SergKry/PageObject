package web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    AuthInfo authInfo;
    VerificationInfo verificationInfo;

    public static class AuthInfo {
        private String login;
        private String password;

        public AuthInfo() {
            this.login = "vasya";
            this.password = "qwerty123";
        }


        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

    }

    public static class VerificationInfo {
        private String code;

        public VerificationInfo() {
            this.code = "12345";
        }

        public VerificationInfo(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static class CardInfo {
        private String cart1;
        private String cart2;


        public CardInfo() {
            this.cart1 = "5559 0000 0000 0001";
            this.cart2 = "5559 0000 0000 0002";
        }

        public String getNumber1() {
            return cart1;
        }

        public String getNumber2() {
            return cart2;
        }
    }
}