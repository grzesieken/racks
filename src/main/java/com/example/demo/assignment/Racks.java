package com.example.demo.assignment;

import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import lombok.Value;

@Value
class Racks {

    Set<Rack> racks;

    Either<AssignmentError, RackUpdate> assign(PatientMetadata patientMetadata) {
        return Stream.ofAll(racks)
                .find(rack -> rack.canAssign(patientMetadata))
                .toEither(() -> AssignmentError.of("No matching rack found"))
                .flatMap(it -> it.update(patientMetadata));
    }

    @Value
    private static class Rack {

        RackId rackId;
        int size;
        Set<PatientMetadata> patients;

        private boolean canAssign(PatientMetadata patientMetadata) {
            return patients.size() < size
                    && Stream.ofAll(patients).find(it -> it.matches(patientMetadata)).isDefined();
        }

        private Either<AssignmentError, RackUpdate> update(PatientMetadata patientMetadata) {
            return Either.right(new RackUpdate(this.rackId, patientMetadata));
        }
    }

    @Value
    public static class RackUpdate {
        RackId rackId;
        PatientMetadata patientMetadata;
    }
}

