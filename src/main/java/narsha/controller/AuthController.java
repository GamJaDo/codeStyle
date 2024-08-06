package narsha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import narsha.dto.caregiver.CaregiverLoginRequest;
import narsha.dto.caregiver.CaregiverRegisterRequest;
import narsha.dto.patient.PatientLoginRequest;
import narsha.dto.patient.PatientRegisterRequest;
import narsha.service.CaregiverAuthService;
import narsha.service.PatientAuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class AuthController {

    private final PatientAuthService patientAuthService;
    private final CaregiverAuthService caregiverAuthService;
    

    public AuthController(PatientAuthService patientAuthService, CaregiverAuthService caregiverAuthService) {
        this.patientAuthService = patientAuthService;
        this.caregiverAuthService = caregiverAuthService;
    }

    @Operation(summary = "환자 등록", description = "새로운 환자 계정을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "환자 등록 성공")
    @PostMapping("/patient/register")
    public ResponseEntity<Void> patientRegister(
            @RequestPart("patient") PatientRegisterRequest request,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            BindingResult bindingResult) {
        patientAuthService.createPatient(request, profileImage, bindingResult);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "환자 로그인", description = "환자가 계정으로 로그인합니다.")
    @ApiResponse(responseCode = "200", description = "환자 로그인 성공")
    @PostMapping("/patient/login")
    public ResponseEntity<Void> patientLogin(
            @RequestBody PatientLoginRequest request,
            BindingResult bindingResult, HttpSession session) {
        patientAuthService.loginPatient(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // ↑ Patient
    /*#####################################################################################*/
    // ↓ Caregiver

    @Operation(summary = "간병인 등록", description = "새로운 간병인 계정을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 등록 성공")
    @PostMapping("/caregiver/register")
    public ResponseEntity<Void> caregiverRegister(
            @RequestPart("caregiver") CaregiverRegisterRequest request,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            BindingResult bindingResult) {
        caregiverAuthService.createCaregiver(request, profileImage, bindingResult);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "간병인 로그인", description = "간병인이 계정으로 로그인합니다.")
    @ApiResponse(responseCode = "200", description = "간병인 로그인 성공")
    @PostMapping("/caregiver/login")
    public ResponseEntity<Void> caregiverLogin(
            @RequestBody CaregiverLoginRequest request,
            BindingResult bindingResult, HttpSession session) {
        caregiverAuthService.loginCaregiver(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
