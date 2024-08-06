package narsha.controller;

import narsha.dto.CareOfferRequest;
import narsha.dto.CareOfferResponse;
import narsha.dto.CareEnrollmentRequest;
import narsha.service.CareEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/care-enrollment")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class CareEnrollmentController {

    private final CareEnrollmentService careEnrollmentService;

    public CareEnrollmentController(CareEnrollmentService careEnrollmentService) {
        this.careEnrollmentService = careEnrollmentService;
    }

    @Operation(summary = "구인 게시글에 신청", description = "간병인이 특정 구인 게시글에 신청합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "신청 성공"),
        @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/guin/{id}/apply")
    public ResponseEntity<Void> applyToGuin(@PathVariable Long id, @RequestBody CareOfferRequest request, HttpSession session) {
        careEnrollmentService.applyToGuin(id, request, session);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "구직 게시글에 제안", description = "환자가 특정 구직 게시글에 간병인을 제안합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "제안 성공"),
        @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/gujik/{id}/propose")
    public ResponseEntity<Void> proposeToCaregiver(@PathVariable Long id, @RequestBody CareEnrollmentRequest request, HttpSession session) {
        careEnrollmentService.proposeToCaregiver(id, request, session);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "간병 신청 승인", description = "환자가 간병 신청을 승인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "승인 성공"),
        @ApiResponse(responseCode = "404", description = "신청을 찾을 수 없음")
    })
    @PostMapping("/enrollment/{id}/approve")
    public ResponseEntity<Void> approveEnrollment(@PathVariable Long id, HttpSession session) {
        careEnrollmentService.approveEnrollment(id, session);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "간병 신청 거절", description = "환자가 간병 신청을 거절합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "거절 성공"),
        @ApiResponse(responseCode = "404", description = "신청을 찾을 수 없음")
    })
    @PostMapping("/enrollment/{id}/reject")
    public ResponseEntity<Void> rejectEnrollment(@PathVariable Long id, HttpSession session) {
        careEnrollmentService.rejectEnrollment(id, session);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "받은 간병 신청 조회", description = "간병인이 받은 모든 간병 신청을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @GetMapping("/received-offers")
    public ResponseEntity<List<CareOfferResponse>> viewReceivedCareOffers(HttpSession session) {
        List<CareOfferResponse> offers = careEnrollmentService.viewReceivedCareOffers(session);
        return ResponseEntity.ok(offers);
    }
}
