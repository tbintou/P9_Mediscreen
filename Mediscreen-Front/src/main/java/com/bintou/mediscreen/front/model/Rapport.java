package com.bintou.mediscreen.front.model;

import com.bintou.mediscreen.rapport.model.Status;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Rapport {

    private String firstName;

    private String lastName;

    private String gender;

    private Long age;

    private Status status;
}
