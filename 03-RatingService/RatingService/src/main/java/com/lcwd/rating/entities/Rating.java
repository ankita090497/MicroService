package com.lcwd.rating.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;
    @Column(name = "user_Id")
    private String userId;
    @Column(name = "hotel_Id")
    private String hotelId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "feedback")
    private String feedback;

}
