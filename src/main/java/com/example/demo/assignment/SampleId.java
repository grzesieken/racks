package com.example.demo.assignment;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Objects;

import static java.lang.String.format;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class SampleId {

    String value;
    static Either<AssignmentError, SampleId> create(String sampleId) {
        if (!isValid(sampleId)) {
            return Either.left(AssignmentError.of(format("SampleId cannot be empty (%s)", sampleId)));
        }
        return Either.right(new SampleId(sampleId));
    }

    private static boolean isValid(String sampleId) {
        return Objects.nonNull(sampleId) && !sampleId.trim().isEmpty();
    }
}
