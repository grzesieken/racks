package com.example.demo.assignment;

import com.example.demo.assignment.AssignmentService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/assign")
    Either<ApiError, AssignmentResponse> assign(@RequestBody AssignmentRequest request) {
        return assignmentService.assign(request.sampleId)
                .bimap(
                     assignmentError -> new ApiError(assignmentError.getMessage()),
                     rackUpdate -> new AssignmentResponse(rackUpdate.getRackId().getValue())
                );
    }

    @Value
    private static class AssignmentRequest {
        String sampleId;
    }

    @Value
    private static class ApiError {
        String message;
    }

    @Value
    private static class AssignmentResponse {
        String rackId;
    }
}
