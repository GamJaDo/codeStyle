package narsha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import narsha.service.GujikService;
import narsha.service.GuinService;
import narsha.dto.caregiver.CaregiverInfoResponse;
import narsha.dto.guin.GuinRequest;
import narsha.dto.guin.GuinResponse;
import narsha.dto.guin.GuinUpdateRequest;
import narsha.dto.gujik.GujikRequest;
import narsha.dto.gujik.GujikResponse;
import narsha.dto.gujik.GujikUpdateRequest;
import narsha.dto.patient.PatientInfoResponse;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class PostController {

    private final GujikService gujikService;
    private final GuinService guinService;

    public PostController(GujikService gujikService, GuinService guinService) {
        this.gujikService = gujikService;
        this.guinService = guinService;
    }

    @Operation(summary = "구직 글 생성", description = "새로운 구직 글을 작성합니다.")
    @ApiResponse(responseCode = "201", description = "구직 글 생성 성공")
    @PostMapping("/gujik/create")
    public ResponseEntity<Void> createGujik(@RequestPart("gujik") GujikRequest request,
    		@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
    		HttpSession session) {
        gujikService.createGujik(request, session);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "모든 구직 글 조회", description = "모든 구직 글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "구직 글 목록 조회 성공")
    @GetMapping("/gujik/list")
    public ResponseEntity<List<GujikResponse>> getAllGujiks() {
        List<GujikResponse> responses = gujikService.getAllGujiks();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @Operation(summary = "구직 글 조회", description = "특정 구직 글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "구직 글 조회 성공")
    @GetMapping("/gujik/{id}")
    public ResponseEntity<GujikResponse> getGujikById(@PathVariable Long id) {
        GujikResponse response = gujikService.getGujikById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @Operation(summary = "구직 글 작성자 프로필 조회", description = "특정 구직 글을 작성한 사용자의 프로필을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "작성자 프로필 조회 성공")
    @GetMapping("/gujik/{id}/author")
    public ResponseEntity<CaregiverInfoResponse> getGujikAuthorProfile(@PathVariable Long id) {
        CaregiverInfoResponse authorProfile = gujikService.getAuthorProfileByGujikId(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorProfile);
    }

    @Operation(summary = "구직 글 수정", description = "특정 구직 글을 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "구직 글 수정 성공"),
        @ApiResponse(responseCode = "404", description = "구직 글을 찾을 수 없음")
    })
    @PutMapping("/gujik/update/{id}")
    public ResponseEntity<Void> updateGujik(@PathVariable Long id, @RequestBody GujikUpdateRequest request, HttpSession session) {
        gujikService.updateGujik(id, request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "구직 글 삭제", description = "특정 구직 글을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "구직 글 삭제 성공"),
        @ApiResponse(responseCode = "404", description = "구직 글을 찾을 수 없음")
    })
    @DeleteMapping("/gujik/delete/{id}")
    public ResponseEntity<Void> deleteGujik(@PathVariable Long id, HttpSession session) {
        gujikService.deleteGujik(id, session);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // ↑ Gujik
    /*#####################################################################################*/
    // ↓ Guin

    @Operation(summary = "구인 글 생성", description = "새로운 구인 글을 작성합니다.")
    @ApiResponse(responseCode = "201", description = "구인 글 생성 성공")
    @PostMapping("/guin/create")
    public ResponseEntity<Void> createGuin(@RequestPart("guin") GuinRequest request,
    		@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
    		HttpSession session) {
        guinService.createGuin(request, session);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "모든 구인 글 조회", description = "모든 구인 글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "구인 글 목록 조회 성공")
    @GetMapping("/guin/list")
    public ResponseEntity<List<GuinResponse>> getAllGuins() {
        List<GuinResponse> responses = guinService.getAllGuins();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @Operation(summary = "구인 글 조회", description = "특정 구인 글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "구인 글 조회 성공")
    @GetMapping("/guin/{id}")
    public ResponseEntity<GuinResponse> getGuinById(@PathVariable Long id) {
        GuinResponse response = guinService.getGuinById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @Operation(summary = "구인 글 작성자 프로필 조회", description = "특정 구인 글을 작성한 사용자의 프로필을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "작성자 프로필 조회 성공")
    @GetMapping("/guin/{id}/author")
    public ResponseEntity<PatientInfoResponse> getGuinAuthorProfile(@PathVariable Long id) {
        PatientInfoResponse authorProfile = guinService.getAuthorProfileByGuinId(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorProfile);
    }

    @Operation(summary = "구인 글 수정", description = "특정 구인 글을 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "구인 글 수정 성공"),
        @ApiResponse(responseCode = "404", description = "구인 글을 찾을 수 없음")
    })
    @PutMapping("/guin/update/{id}")
    public ResponseEntity<Void> updateGuin(@PathVariable Long id, @RequestBody GuinUpdateRequest request, HttpSession session) {
        guinService.updateGuin(id, request, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "구인 글 삭제", description = "특정 구인 글을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "구인 글 삭제 성공"),
        @ApiResponse(responseCode = "404", description = "구인 글을 찾을 수 없음")
    })
    @DeleteMapping("/guin/delete/{id}")
    public ResponseEntity<Void> deleteGuin(@PathVariable Long id, HttpSession session) {
        guinService.deleteGuin(id, session);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
