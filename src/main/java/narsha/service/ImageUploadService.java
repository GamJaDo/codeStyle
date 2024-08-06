package narsha.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import narsha.entity.Post;
import narsha.entity.User;
import narsha.exception.ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageUploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    public ImageUploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public <T extends User> void profileImageUrlSave(T user, MultipartFile profileImage) {
        user.setProfileImageUrl(
                "https://likelion12-team-project-bucket.s3.ap-northeast-2.amazonaws.com/default_profile.png");
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = uploadFile(profileImage);
            user.setProfileImageUrl(imageUrl);
        }
    }
    
    public <T extends Post<?, ?>> void postImageUrlSave(T post, MultipartFile postImage) {
        post.setPostImageUrl(
                "https://likelion12-team-project-bucket.s3.ap-northeast-2.amazonaws.com/default_post_image.png");
        if (postImage != null && !postImage.isEmpty()) {
            String imageUrl = uploadFile(postImage);
            post.setPostImageUrl(imageUrl);
        }
    }

    private String uploadFile(MultipartFile file) {
        try {
            String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(bucket, key, file.getInputStream(), metadata);
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, key);
        } catch (IOException e) {
            throw new ImageUploadException("Upload failed.");
        }
    }
}
