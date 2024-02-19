package com.example.demo.assignment;

import io.vavr.control.Either;

interface PatientRepository {
    Either<AssignmentError, PatientMetadata> findBy(SampleId sampleId);

}
