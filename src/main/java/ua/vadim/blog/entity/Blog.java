package ua.vadim.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//@Entity
//@Table(name = "blogs")
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {


    @Id
    //@Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    //@Column
    private String title;

    //@Column
    private String author;

    //@Column
    private String text;

    //@Column
    private Date date;

    //@Column
    private Boolean visiable;

}
