package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.data.DataGenerator.generateDate;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = generateDate(daysToAddForSecondMeeting);

        $x("//*[@data-test-id='city']//input").setValue(validUser.getCity());
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").setValue(firstMeetingDate);
        $x("//*[@data-test-id='name']//input").setValue(validUser.getName());
        $x("//*[@data-test-id='phone']//input").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']//*[@class='checkbox__box']").click();
        $x("//span[text() = 'Запланировать']").click();
        $x("//*[@data-test-id='success-notification']//div[@class = 'notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        $x("//span[text() = 'Запланировать']").click();
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").setValue(secondMeetingDate);
        $x("//*[@data-test-id='replan-notification']//button").click();
        $x("//*[@data-test-id='success-notification']//div[@class = 'notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));
    }
}