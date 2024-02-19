package com.example.demo.assignment;

import io.vavr.control.Either;

interface RackRepository {
    Either<AssignmentError, Racks> getRacks();

    Either<AssignmentError, Racks.RackUpdate> save(Racks.RackUpdate racksUpdated);
}
