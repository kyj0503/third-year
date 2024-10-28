package edu;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Slf4j
public class Student {
    private Long id;
    private String firstName;
    private String lastName;

    public void logInfo() {
        log.info("Student id: {}, name: {}{}", id, firstName, lastName);
    }
}
