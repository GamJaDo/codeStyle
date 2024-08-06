package narsha.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import narsha.dto.CareEnrollmentRequest;
import narsha.dto.CareEnrollmentResponse;
import narsha.dto.CareOfferRequest;
import narsha.dto.CareOfferResponse;
import narsha.service.CareEnrollmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/care-enrollment")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class CareEnrollmentController {

    private final CareEnrollmentService careEnrollmentService;

    public CareEnrollmentController(CareEnrollmentService careEnrollmentService) {
        this.careEnrollmentService = careEnrollmentService;
    }

    @Operation(summary = "간병 신청 제출", description = "환자가 간병 신청을 제출합니다.")
    @ApiResponse(responseCode = "201", description = "간병 신청 성공")
    @PostMapping("/submit")
    public ResponseEntity<Void> submitEnrollment(@RequestBody CareEnrollmentRequest request, HttpSession session) {
        careEnrollmentService.submitEnrollment(request, session);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "받은 간병 신청 조회", description = "간병인이 받은 간병 신청 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "간병 신청 목록 조회 성공")
    @GetMapping("/received")
    public ResponseEntity<List<CareEnrollmentResponse>> viewReceivedEnrollments(HttpSession session) {
        List<CareEnrollmentResponse> enrollments = careEnrollmentService.viewReceivedEnrollments(session);
        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "간병 신청 승인", description = "간병인이 간병 신청을 승인합니다.")
    @ApiResponse(responseCode = "200", description = "간병 신청 승인 성공")
    @PostMapping("/approve/{id}")
    public ResponseEntity<Void> approveEnrollment(@PathVariable Long id,
                                                  @RequestBody CareOfferRequest request, HttpSession session) {
        careEnrollmentService.approveEnrollment(id, request, session);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "승인된 간병 상세 정보 조회", description = "환자가 승인된 간병의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "간병 상세 정보 조회 성공")
    @GetMapping("/approved-care-detail")
    public ResponseEntity<CareOfferResponse> viewApprovedCareDetail(HttpSession session) {
        CareOfferResponse careDetail = careEnrollmentService.getApprovedCareDetail(session);
        return ResponseEntity.ok(careDetail);
    }
}
