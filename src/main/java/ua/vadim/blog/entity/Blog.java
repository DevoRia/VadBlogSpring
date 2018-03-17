package ua.vadim.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    private String title;

    private String author;

    private String text;

    private Date date;

    private Boolean visiable;
}
