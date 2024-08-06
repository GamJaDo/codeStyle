package narsha.service;

import narsha.dto.guin.GuinRequest;
import narsha.dto.guin.GuinResponse;
import narsha.dto.guin.GuinUpdateRequest;
import narsha.dto.patient.PatientInfoResponse;
import narsha.entity.Guin;
import narsha.entity.Patient;
import narsha.repository.GuinRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Service
public class GuinService extends PostService<Guin, GuinRequest, GuinResponse, Patient> {

    public GuinService(GuinRepository guinRepository, ImageUploadService imageUploadService) {
        super(guinRepository, imageUploadService, Patient.class);
    }

    public void createGuin(GuinRequest request, MultipartFile postImage, HttpSession session) {
        createPost(request, postImage, session);
    }

    public GuinResponse getGuinById(Long id) {
        return getPostById(id);
    }

    public List<GuinResponse> getAllGuins() {
        return getAllPosts();
    }

    public void updateGuin(Long id, GuinUpdateRequest request, MultipartFile postImage, HttpSession session) {
        updatePost(id, request, postImage, session);
    }

    public void deleteGuin(Long id, HttpSession session) {
        deletePost(id, session);
    }
    
    public PatientInfoResponse getAuthorProfileByGuinId(Long id) {
        Guin guin = findPostById(id);
        Patient author = guin.getAuthor();
        return author.toDto();
    }
}
