package com.example.demo.assignment;

import io.vavr.NotImplementedError;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;

@Repository
class RackRepositoryInMemory implements RackRepository {

    @Override
    public Either<AssignmentError, Racks> getRacks() {
        throw new NotImplementedError(); // TODO
    }

    @Override
    public Either<AssignmentError, Racks.RackUpdate> save(Racks.RackUpdate racksUpdated) {
        throw new NotImplementedError(); // TODO
    }
}
