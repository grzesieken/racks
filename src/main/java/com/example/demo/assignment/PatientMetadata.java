package com.example.demo.assignment;

import lombok.Value;

import java.util.Objects;

@Value
class PatientMetadata {

    // should introduce value objects
    int age;
    String company;
    String district;
    String visionDefect;

    boolean matches(PatientMetadata other) {
        return this.age == other.age
                && Objects.equals(this.company, other.company)
                && Objects.equals(this.district, other.district)
                && Objects.equals(this.visionDefect, other.visionDefect);
    }
}
