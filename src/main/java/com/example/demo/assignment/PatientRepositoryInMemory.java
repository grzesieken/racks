package com.example.demo.assignment;

import io.vavr.NotImplementedError;
import io.vavr.collection.HashMap;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;

@Repository
class PatientRepositoryInMemory implements PatientRepository {

    private final HashMap<SampleId, PatientMetadata> patientsData = HashMap.empty();

    @Override
    public Either<AssignmentError, PatientMetadata> findBy(SampleId sampleId) {
        return patientsData.get(sampleId)
                .toEither(() -> AssignmentError.of(String.format("Patient data not found, %s", sampleId)));
    }
}
