package narsha.service;

import narsha.dto.PostRequest;
import narsha.dto.PostResponse;
import narsha.dto.PostUpdateRequest;
import narsha.entity.Post;
import narsha.entity.User;
import narsha.exception.InvalidUserException;
import narsha.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PostService<T extends Post<U, S>, R extends PostRequest<T, U>, S extends PostResponse<U>, U extends User> {

    protected final PostRepository<T> postRepository;
    private final ImageUploadService imageUploadService;
    private final Class<U> userType;

    public PostService(PostRepository<T> postRepository, ImageUploadService imageUploadService, Class<U> userType) {
        this.postRepository = postRepository;
        this.imageUploadService = imageUploadService;
        this.userType = userType;
    }

    public List<S> getAllPosts() {
        return postRepository.findAll().stream()
                .map(T::toDto)
                .collect(Collectors.toList());
    }

    public S getPostById(Long id) {
        T post = findPostById(id);
        return post.toDto();
    }

    public void createPost(R request, MultipartFile postImage, HttpSession session) {
    	U author = getSessionUser(session);
        T post = request.toEntity(author);
        imageUploadService.postImageUrlSave(post, postImage);

        postRepository.save(post);
    }

    public void updatePost(Long id, PostUpdateRequest<T> request, MultipartFile postImage, HttpSession session) {
        T post = findPostById(id);
        validateAuthor(session, post.getAuthor());

        String imageUrl = null;
        if (request.getPostImageUrl() != null) {
            imageUrl = request.getPostImageUrl();
        }

        request.toEntity(post);
        if (imageUrl != null) {
            post.setPostImageUrl(imageUrl);
        }
        postRepository.save(post);
    }

    public void deletePost(Long id, HttpSession session) {
        T post = findPostById(id);
        validateAuthor(session, post.getAuthor());
        postRepository.delete(post);
    }

    protected T findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
    }

    protected U getSessionUser(HttpSession session) {
        Object userObject = session.getAttribute("user");
        if (!userType.isInstance(userObject)) {
            throw new InvalidUserException("User not found in session.", HttpStatus.UNAUTHORIZED);
        }
        return userType.cast(userObject);
    }

    protected void validateAuthor(HttpSession session, U author) {
        U sessionUser = getSessionUser(session);
        if (!author.equals(sessionUser)) {
            throw new InvalidUserException("You are not authorized to modify this post.", HttpStatus.FORBIDDEN);
        }
    }
}
