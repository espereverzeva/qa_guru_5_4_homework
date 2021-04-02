package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {
    String firstName = "Anton",
            lastName = "Antonov",
            email = "abc@aa.com",
            gender = "Male",
            mobile = "1234567890",
            dayOfBirch = "18",
            monthOfBirch = "May",
            yearOfBirch = "1988",
            dayOfWeekOfBirch = "Wednesday",
            subject1 = "Chemistry",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "123.png",
            currentAddress = "Montenegro 150",
            state = "Uttar Pradesh",
            city = "Agra";


    @Test
    void successfulRegistrationTest() {
        // Given
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // When
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("[for = gender-radio-1]").click();
        // $("#gender-radio-1").doubleClick();
        // $(byText(gender)).click();
        $("#userNumber").val(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirch);
        $(".react-datepicker__year-select").selectOption(yearOfBirch);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']", dayOfWeekOfBirch, monthOfBirch, dayOfBirch, yearOfBirch)).click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        // Then
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(mobile),
                text(dayOfBirch + " " + monthOfBirch+ "," + yearOfBirch),
                text(subject1),
                text(hobby1 + ", " + hobby2 + "," + " " + hobby3),
                text(picture),
                text(currentAddress),
                text(state + " " + city)
        );
        $("#closeLargeModal").click();
    }
}



