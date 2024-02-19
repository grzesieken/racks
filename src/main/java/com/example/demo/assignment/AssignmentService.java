package com.example.demo.assignment;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final RackRepository rackRepository;
    private final PatientRepository patientRepository;

    public Either<AssignmentError, Racks.RackUpdate> assign(String sampleId) {
        try {
            val validSampleId = SampleId.create(sampleId).fold(err -> { throw err; }, it -> it);
            val patientMetadata = patientRepository.findBy(validSampleId).fold(err -> { throw err; }, it -> it);
            val racks = rackRepository.getRacks().fold(err -> { throw err; }, it -> it);
            return racks.assign(patientMetadata).flatMap(rackRepository::save);
        } catch (AssignmentError e) {
            return Either.left(e);
        }
    }
}
