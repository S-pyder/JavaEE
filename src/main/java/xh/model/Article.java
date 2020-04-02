package xh.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Article {
    private Long id;

    private Long userId;

    private String coverImage;

    private Long categoryId;

    private Byte status;

    private String title;

    private String content;

    private Long viewCount;

    private Date createdAt;

    private Date updatedAt;

    private User author;
    private Long commentCount;
    private List<Comment> commentList;
}