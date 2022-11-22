package com.project.reviewSite_backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReviewSiteBackendApplicationTests {

    @Test
    @DisplayName("persist-test")
    void test1() {
    }
}