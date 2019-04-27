package gykizmir.com.geziuygulamasi;

/**
 * Created by Gamze on 4/21/2019.
 */

public class Post {

    private String postName;
    private String postDescription;
    private int postImage;

    public Post() {
    }

    public Post(String postName, String postDescription, int postImage) {
        this.postName = postName;
        this.postDescription = postDescription;
        this.postImage = postImage;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
}
