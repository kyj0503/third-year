package edu;

import jakarta.persistence.*;

public class JPA_ORM {

    //Entity로서 Table로 생성한다.
    @Entity
    public class JPA_MODEL {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        //id가 자동으로 생성 id
        @Column
        private String firstName;

        @Column
        private String lastName;
    }
}
