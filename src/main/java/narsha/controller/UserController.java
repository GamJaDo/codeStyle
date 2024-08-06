package narsha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import narsha.dto.UserImageUpdateRequest;
import narsha.dto.UserNameUpdateRequest;
import narsha.dto.UserPasswordUpdateRequest;
import narsha.dto.caregiver.CaregiverInfoResponse;
import narsha.dto.caregiver.CaregiverProfileUpdateRequest;
import narsha.dto.patient.PatientInfoResponse;
import narsha.dto.patient.PatientProfileUpdateRequest;
import narsha.entity.Caregiver;
import narsha.entity.Patient;
import narsha.service.CaregiverService;
import narsha.service.LoginService;
import narsha.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class UserController {

    private final PatientService patientService;
    private final CaregiverService caregiverService;
    private final LoginService loginService;

    public UserController(PatientService patientService, CaregiverService caregiverService,
    		LoginService loginService) {
        this.patientService = patientService;
        this.caregiverService = caregiverService;
        this.loginService = loginService;
    }
    
    @Operation(summary = "로그인 상태 확인", description = "현재 사용자의 로그인 상태를 확인합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그인 상태 확인 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @PostMapping("/me")
    public ResponseEntity<Void> checkLoginStatus(HttpSession session){
        loginService.checkLoginStatus(session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @Operation(summary = "로그아웃", description = "현재 사용자를 로그아웃합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        loginService.logout(session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "프로필 조회", description = "현재 사용자의 프로필을 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로필 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    @GetMapping("/profile")
    public ResponseEntity<?> viewProfile(HttpSession session) {
        Object user = session.getAttribute("user");

        if (user instanceof Patient) {
            return viewPatientProfile(session);
        } else if (user instanceof Caregiver) {
            return viewCaregiverProfile(session);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
        }
    }

    @Operation(summary = "환자 프로필 컨트롤 조회", description = "환자의 프로필 컨트롤을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "환자 프로필 컨트롤 조회 성공")
    @GetMapping("/patient/control")
    public ResponseEntity<PatientInfoResponse> viewPatientControl(HttpSession session) {
        PatientInfoResponse patientInfo = patientService.getPatientInfo(session);
        return ResponseEntity.status(HttpStatus.OK).body(patientInfo);
    }

    @Operation(summary = "환자 이미지 업데이트", description = "환자의 프로필 이미지를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "환자 이미지 업데이트 성공")
    @PostMapping("/patient/image-update")
    public ResponseEntity<Void> patientImageUpdate(@RequestBody UserImageUpdateRequest request, HttpSession session) {
        patientService.patientImageUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "환자 이름 업데이트", description = "환자의 이름을 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "환자 이름 업데이트 성공")
    @PostMapping("/patient/name-update")
    public ResponseEntity<Void> patientNameUpdate(@RequestBody UserNameUpdateRequest request, HttpSession session) {
        patientService.patientNameUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "환자 비밀번호 업데이트", description = "환자의 비밀번호를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "환자 비밀번호 업데이트 성공")
    @PostMapping("/patient/password-update")
    public ResponseEntity<Void> patientPasswordUpdate(@RequestBody UserPasswordUpdateRequest request, HttpSession session) {
        patientService.patientPasswordUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "환자 프로필 업데이트", description = "환자의 프로필을 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "환자 프로필 업데이트 성공")
    @PostMapping("/patient/profile-update")
    public ResponseEntity<Void> patientProfileUpdate(@RequestBody PatientProfileUpdateRequest request, HttpSession session) {
        patientService.patientProfileUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /* @GetMapping("/patient/profile") */
    public ResponseEntity<PatientInfoResponse> viewPatientProfile(HttpSession session) {
        PatientInfoResponse patientInfo = patientService.getPatientInfo(session);
        return ResponseEntity.status(HttpStatus.OK).body(patientInfo);
    }

    // ↑ Patient
    /*#####################################################################################*/
    // ↓ Caregiver

    @Operation(summary = "간병인 프로필 컨트롤 조회", description = "간병인의 프로필 컨트롤을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 프로필 컨트롤 조회 성공")
    @GetMapping("/Caregiver/control")
    public ResponseEntity<CaregiverInfoResponse> viewCaregiverControl(HttpSession session) {
        CaregiverInfoResponse caregiverInfo = caregiverService.getCaregiverInfo(session);
        return ResponseEntity.status(HttpStatus.OK).body(caregiverInfo);
    }

    @Operation(summary = "간병인 이미지 업데이트", description = "간병인의 프로필 이미지를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 이미지 업데이트 성공")
    @PostMapping("/Caregiver/image-update")
    public ResponseEntity<Void> caregiverImageUpdate(@RequestBody UserImageUpdateRequest request, HttpSession session) {
        caregiverService.caregiverImageUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "간병인 이름 업데이트", description = "간병인의 이름을 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 이름 업데이트 성공")
    @PostMapping("/Caregiver/name-update")
    public ResponseEntity<Void> caregiverNameUpdate(@RequestBody UserNameUpdateRequest request, HttpSession session) {
        caregiverService.caregiverNameUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "간병인 비밀번호 업데이트", description = "간병인의 비밀번호를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 비밀번호 업데이트 성공")
    @PostMapping("/Caregiver/password-update")
    public ResponseEntity<Void> caregiverPasswordUpdate(@RequestBody UserPasswordUpdateRequest request, HttpSession session) {
        caregiverService.caregiverPasswordUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "간병인 프로필 업데이트", description = "간병인의 프로필을 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 프로필 업데이트 성공")
    @PostMapping("/Caregiver/profile-update")
    public ResponseEntity<Void> caregiverProfileUpdate(@RequestBody CaregiverProfileUpdateRequest request, HttpSession session) {
        caregiverService.caregiverProfileUpdate(request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /* @GetMapping("/Caregiver/profile") */
    public ResponseEntity<CaregiverInfoResponse> viewCaregiverProfile(HttpSession session) {
        CaregiverInfoResponse caregiverInfo = caregiverService.getCaregiverInfo(session);
        return ResponseEntity.status(HttpStatus.OK).body(caregiverInfo);
    }
}
