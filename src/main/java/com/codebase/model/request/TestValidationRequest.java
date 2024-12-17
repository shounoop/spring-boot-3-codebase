package com.codebase.model.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TestValidationRequest {

    public interface AllLevels {
    }

    public interface Junior {
    }

    public interface MidSenior {
    }

    public interface Senior {
    }

    @Size(min = 5, max = 20, message = "Name must be between 5 and 20 character")
    @Pattern(regexp = "[^0-9]*", message = "Name must not contain numbers")
    @NotBlank(message = "Name is mandatory field")
    String name;

    @Min(value = 5, groups = Junior.class, message = "Junior level requires at least 5 years of experience")
    @Min(value = 10, groups = MidSenior.class, message = "Mid-Senior level requires at least 10 years of experience")
    @Min(value = 15, groups = Senior.class, message = "Senior level requires at least 15 years of experience")
    int exp;

    @AssertTrue(message = "You are not admin")
    boolean isAdmin;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must have exactly 10 digits")
    String mobileNumber;

    public TestValidationRequest(String name, int exp, boolean isAdmin, String mobileNumber) {
        this.name = name;
        this.exp = exp;
        this.isAdmin = isAdmin;
        this.mobileNumber = mobileNumber;
    }

    public void dummy() {
        System.out.println("Dummy method running, " + name);
    }
}