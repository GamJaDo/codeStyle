package narsha.service;

import narsha.dto.caregiver.CaregiverInfoResponse;
import narsha.dto.gujik.GujikRequest;
import narsha.dto.gujik.GujikResponse;
import narsha.dto.gujik.GujikUpdateRequest;
import narsha.entity.Gujik;
import narsha.entity.Caregiver;
import narsha.repository.GujikRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Service
public class GujikService extends PostService<Gujik, GujikRequest, GujikResponse, Caregiver> {

    public GujikService(GujikRepository gujikRepository, ImageUploadService imageUploadService) {
        super(gujikRepository, imageUploadService, Caregiver.class);
    }

    public void createGujik(GujikRequest request, MultipartFile postImage, HttpSession session) {
        createPost(request, postImage, session);
    }

    public GujikResponse getGujikById(Long id) {
        return getPostById(id);
    }

    public List<GujikResponse> getAllGujiks() {
        return getAllPosts();
    }

    public void updateGujik(Long id, GujikUpdateRequest request, MultipartFile postImage, HttpSession session) {
        updatePost(id, request, postImage, session);
    }

    public void deleteGujik(Long id, HttpSession session) {
        deletePost(id, session);
    }
    
    public CaregiverInfoResponse getAuthorProfileByGujikId(Long id) {
        Gujik gujik = findPostById(id);
        Caregiver author = gujik.getAuthor();
        return author.toDto();
    }
}
