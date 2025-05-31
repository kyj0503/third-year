package com.webproject.chonstay_backend.home;

import com.webproject.chonstay_backend.common.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

    List<Home> findByHomeNameContainingIgnoreCaseAndHomeStatusTrue(String searchQuery);

    List<Home> findByDescriptionContainingIgnoreCaseAndHomeStatusTrue(String searchQuery);

    List<Home> findByAddressContainingIgnoreCaseAndHomeStatusTrue(String searchQuery);

    List<Home> findByLocationAndHomeStatusTrue(Location location);

    List<Home> findByHomeNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAddressContainingIgnoreCaseAndLocationAndHomeStatusTrue(
            String searchQuery1, String searchQuery2, String searchQuery3, Location location);

    List<Home> findAllByHomeStatusTrue();
}
